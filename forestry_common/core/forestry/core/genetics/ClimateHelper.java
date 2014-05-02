/*******************************************************************************
 * Copyright 2011-2014 by SirSengir
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivs 3.0 Unported License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/3.0/.
 ******************************************************************************/
package forestry.core.genetics;

import java.util.ArrayList;
import java.util.Locale;

import forestry.api.core.EnumHumidity;
import forestry.api.core.EnumTemperature;
import forestry.api.genetics.EnumTolerance;
import forestry.core.utils.StringUtil;

public class ClimateHelper {

	public static boolean isWithinLimits(EnumTemperature temperature, EnumHumidity humidity,
			EnumTemperature baseTemp, EnumTolerance tolTemp,
			EnumHumidity baseHumid, EnumTolerance tolHumid) {
		if(!getToleratedTemperature(baseTemp, tolTemp).contains(temperature))
			return false;
		
		return getToleratedHumidity(baseHumid, tolHumid).contains(humidity);
	}
	
	public static ArrayList<EnumHumidity> getToleratedHumidity(EnumHumidity prefered, EnumTolerance tolerance) {

		ArrayList<EnumHumidity> tolerated = new ArrayList<EnumHumidity>();
		tolerated.add(prefered);

		switch (tolerance) {

		case BOTH_5:
		case BOTH_4:
		case BOTH_3:
		case BOTH_2:
			if (prefered.ordinal() + 2 < EnumHumidity.values().length)
				tolerated.add(EnumHumidity.values()[prefered.ordinal() + 2]);
			if (prefered.ordinal() - 2 >= 0)
				tolerated.add(EnumHumidity.values()[prefered.ordinal() - 2]);
		case BOTH_1:
			if (prefered.ordinal() + 1 < EnumHumidity.values().length)
				tolerated.add(EnumHumidity.values()[prefered.ordinal() + 1]);
			if (prefered.ordinal() - 1 >= 0)
				tolerated.add(EnumHumidity.values()[prefered.ordinal() - 1]);
			return tolerated;

		case UP_5:
		case UP_4:
		case UP_3:
		case UP_2:
			if (prefered.ordinal() + 2 < EnumHumidity.values().length)
				tolerated.add(EnumHumidity.values()[prefered.ordinal() + 2]);
		case UP_1:
			if (prefered.ordinal() + 1 < EnumHumidity.values().length)
				tolerated.add(EnumHumidity.values()[prefered.ordinal() + 1]);
			return tolerated;

		case DOWN_5:
		case DOWN_4:
		case DOWN_3:
		case DOWN_2:
			if (prefered.ordinal() - 2 >= 0)
				tolerated.add(EnumHumidity.values()[prefered.ordinal() - 2]);
		case DOWN_1:
			if (prefered.ordinal() - 1 >= 0)
				tolerated.add(EnumHumidity.values()[prefered.ordinal() - 1]);
			return tolerated;

		default:
			return tolerated;
		}

	}

	public static ArrayList<EnumTemperature> getToleratedTemperature(EnumTemperature prefered, EnumTolerance tolerance) {

		ArrayList<EnumTemperature> tolerated = new ArrayList<EnumTemperature>();
		tolerated.add(prefered);

		switch (tolerance) {

		case BOTH_5:
			if (prefered.ordinal() + 5 < EnumTemperature.values().length)
				tolerated.add(EnumTemperature.values()[prefered.ordinal() + 5]);
			if (prefered.ordinal() - 5 >= 0)
				tolerated.add(EnumTemperature.values()[prefered.ordinal() - 5]);
		case BOTH_4:
			if (prefered.ordinal() + 4 < EnumTemperature.values().length)
				tolerated.add(EnumTemperature.values()[prefered.ordinal() + 4]);
			if (prefered.ordinal() - 4 >= 0)
				tolerated.add(EnumTemperature.values()[prefered.ordinal() - 4]);
		case BOTH_3:
			if (prefered.ordinal() + 3 < EnumTemperature.values().length)
				tolerated.add(EnumTemperature.values()[prefered.ordinal() + 3]);
			if (prefered.ordinal() - 3 >= 0)
				tolerated.add(EnumTemperature.values()[prefered.ordinal() - 3]);
		case BOTH_2:
			if (prefered.ordinal() + 2 < EnumTemperature.values().length)
				tolerated.add(EnumTemperature.values()[prefered.ordinal() + 2]);
			if (prefered.ordinal() - 2 >= 0)
				tolerated.add(EnumTemperature.values()[prefered.ordinal() - 2]);
		case BOTH_1:
			if (prefered.ordinal() + 1 < EnumTemperature.values().length)
				tolerated.add(EnumTemperature.values()[prefered.ordinal() + 1]);
			if (prefered.ordinal() - 1 >= 0)
				tolerated.add(EnumTemperature.values()[prefered.ordinal() - 1]);
			return tolerated;

		case UP_5:
			if (prefered.ordinal() + 5 < EnumTemperature.values().length)
				tolerated.add(EnumTemperature.values()[prefered.ordinal() + 5]);
		case UP_4:
			if (prefered.ordinal() + 4 < EnumTemperature.values().length)
				tolerated.add(EnumTemperature.values()[prefered.ordinal() + 4]);
		case UP_3:
			if (prefered.ordinal() + 3 < EnumTemperature.values().length)
				tolerated.add(EnumTemperature.values()[prefered.ordinal() + 3]);
		case UP_2:
			if (prefered.ordinal() + 2 < EnumTemperature.values().length)
				tolerated.add(EnumTemperature.values()[prefered.ordinal() + 2]);
		case UP_1:
			if (prefered.ordinal() + 1 < EnumTemperature.values().length)
				tolerated.add(EnumTemperature.values()[prefered.ordinal() + 1]);
			return tolerated;

		case DOWN_5:
			if (prefered.ordinal() - 5 >= 0)
				tolerated.add(EnumTemperature.values()[prefered.ordinal() - 5]);
		case DOWN_4:
			if (prefered.ordinal() - 4 >= 0)
				tolerated.add(EnumTemperature.values()[prefered.ordinal() - 4]);
		case DOWN_3:
			if (prefered.ordinal() - 3 >= 0)
				tolerated.add(EnumTemperature.values()[prefered.ordinal() - 3]);
		case DOWN_2:
			if (prefered.ordinal() - 2 >= 0)
				tolerated.add(EnumTemperature.values()[prefered.ordinal() - 2]);
		case DOWN_1:
			if (prefered.ordinal() - 1 >= 0)
				tolerated.add(EnumTemperature.values()[prefered.ordinal() - 1]);
			return tolerated;

		default:
			return tolerated;
		}
	}

	public static String toDisplay(EnumTemperature temperature) {
		return StringUtil.localize("gui." + temperature.toString().toLowerCase(Locale.ENGLISH));
	}

	public static String toDisplay(EnumHumidity humidity) {
		return StringUtil.localize("gui." + humidity.toString().toLowerCase(Locale.ENGLISH));
	}
}