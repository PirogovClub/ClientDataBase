package utils;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

public class ConverDates {
	
	private static LocalDate localDate;
	private static String formatted;
	private static String outputImproper;
	
	public static LocalDate getLocalDate() {
		return localDate;
	}

	public static String getFormatted() {
		return formatted;
	}

	public static String getOutputImproper() {
		return outputImproper;
	}

	
	public static void setFieldsFromUSLocale(String localdate) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "yyyy-MM-dd" ).withLocale ( Locale.forLanguageTag("EN") );
		
		localDate = LocalDate.parse ( localdate , formatter );
		formatter = DateTimeFormatter.ofPattern ( "dd-MMM-yyyy" ).withLocale ( Locale.forLanguageTag("RU") );
		formatted = formatter.format ( localDate );
		outputImproper = formatted.replace ( "." , "" );
		
	}

	public static LocalDate readDateFromNonRULocale(String input) {
		// Read date with month without dot like окт.
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( "dd-MMM-yyyy" ).withLocale ( Locale.forLanguageTag("RU") );
		localDate = LocalDate.MIN; // Some folks prefer a bogus default value as a success/failure flag rather than using a NULL.
		try {
		    localDate = LocalDate.parse ( input , formatter );
		} catch ( DateTimeParseException e ) {
		    // Look for any month name abbreviation improperly missing the period (FULL STOP).
		    for ( Month month : Month.values () ) {
		        String abbreviation = month.getDisplayName ( TextStyle.SHORT , Locale.forLanguageTag("RU") );
		        String abbreviationWithoutFullStop = abbreviation.replace ( "." , "" ); // Get short abbreviation, but drop any period (FULL STOP).
		        String proper = "-" + abbreviation + "-";
		        String improper = "-" + abbreviationWithoutFullStop + "-";
		        if ( input.contains ( improper ) ) {
		            String inputFixed = input.replace ( improper , proper );
		            try {
		                localDate = LocalDate.parse ( inputFixed , formatter );
		            } catch ( DateTimeParseException e2 ) {
		                // FIXME: Handle this error. We expected this second parse attempt to succeed.
		            }
		            break; // Bail-out of the loop as we got a hit, matching input with a particular improper value.
		        }
		    }
		}
		Boolean success =  ! ( localDate.equals ( LocalDate.MIN ) );
		formatted = formatter.format ( localDate );;
		outputImproper = formatted.replace ( "." , "" );  // Drop any period (FULL STOP).
		System.out.println ( "success: " + success + ". input: " + input + " → localDate: " + localDate + " → formatted: " + formatted + " → outputImproper: " + outputImproper );
		return localDate;

		
	}
	
	public static String readDateFromNonRULocaleToString(String input, String toPattern, Locale locale) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern ( toPattern ).withLocale ( locale );
		String output = formatter.format(readDateFromNonRULocale(input));
		System.out.println ("Send converted date "+output);
		return output;
		
	}

}
