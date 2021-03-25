package csci240.prinCad.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import csci240.prinCad.ui.MainForm;

public class Log {

	// Base Interface for logging - exposes error and info logging
	private interface Logging {
		// write error to log gile
		public void error(String errorText);

		// Write exception to log file
		public void error(String text, Exception ex);

		// Write info to log file
		public void info(String infoText);
	}

	// No Logging
	private class LogNone implements Logging {
		// write error to log gile
		public void error(String errorText) {

		}

		// Write exception to log file
		public void error(String text, Exception ex) {

		}

		// Write info to log file
		public void info(String infoText) {

		}
	}

	// Error Logging
	private class LogError implements Logging {

		// Write error to log file
		public void error(String text, Exception ex) {

			String errorText = String.format("%s %s", text, ex.toString());
			error(errorText);
		}

		// Write to Log File
		public void info(String infoText) {

		}

		public void error(String errorText) {
			write(errorText);
		}
	}

	// Info Logging
	private class LogInfo implements Logging {

		// Write error to log file
		public void error(String errorText) {
			write(errorText);
		}

		// Write exception to log file
		public void error(String text, Exception ex) {

			String errorText = String.format("%s %s", text, ex.toString());
			error(errorText);
		}

		// Write info to log file
		public void info(String infoText) {
			write(infoText);
		}
	}

	public enum LoggingLevel {
		None, Error, Information
	}

	// The one and only instance of Log object - singleton design pattern
	private static Log _instance = new Log();

	// The logging level instance - result of the factory design pattern
	Logging _logging;

	// Prinvate constructor to create the correct logging level object
	private Log() {

		// Factory design pattern
		LoggingLevel loggingLevel = MainForm.appSettings.getLoggingLevel();

		switch (loggingLevel) {
		case None:
			_logging = new LogNone();
			break;
		case Error:
			_logging = new LogError();
			break;
		case Information:
			_logging = new LogInfo();
			break;
		}
	}

	// Write error to log file
	public static void error(String errorText) {
		_instance._logging.error(errorText);
	}
	
	//Write exception to log file
	public static void error(String text, Exception ex) {
		_instance._logging.error(text, ex);
	}
	
	//Write info to log file
	public static void info(String infoText) {
		_instance._logging.info(infoText);
	}


	// path to log file
	final static String LogFilePath = "PrinCad.log";

	// Create formatter
	final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

	// Write text to log file
	public static void write(String text) {
		try {
			File file = new File(LogFilePath);
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw, true);

			// Local date time instance
			LocalDateTime localDateTime = LocalDateTime.now();

			// Get formatted String
			String ldtString = dateTimeFormatter.format(localDateTime);

			out.println(String.format("%s - %s", ldtString, text));

			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
