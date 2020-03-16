package parkingtoll.util;

import java.util.concurrent.TimeUnit;

/**
 * Utility class for converting elapsed time from nanoseconds to other unity of
 * time.
 * 
 * @author jlm
 *
 */
public class Utils {

	/**
	 * get number of hour elapsed between start and end. Parameter should be time
	 * representation in nano seconds.
	 * 
	 * @param start, start of event
	 * @param end,   end of event
	 * @return duration of the event in hours.
	 */
	public static long getElapsedHours(long start, long end) {
		return TimeUnit.NANOSECONDS.convert(end - start, TimeUnit.HOURS);
	}

	/**
	 * get number of hour elapsed between start and end. Parameter should be time
	 * representation in nano seconds.
	 * 
	 * @param start, start of event
	 * @param end,   end of event
	 * @return duration of the event in minutes.
	 */
	public static long getElapsedMin(long start, long end) {
		return TimeUnit.NANOSECONDS.convert(end - start, TimeUnit.MINUTES);
	}
}
