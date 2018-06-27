/**
 * 
 */
package com.dragon.framework;

/**
 * @author M1030482
 *
 */
public class Enums {

	// Enum for Mouse Click Options
	public enum MouseClick {
		// For Single Mouse Click
		SINGLE,
		// For Double Mouse Click
		DOUBLE,
		// For Right Mouse Click
		RIGHT
	}

	public enum CheckedStatus {
		// For Checking the CheckBox
		CHECK,
		// For unchecking the Check box
		UNCHECK
	}

	public enum EndAction {
		// For Enter Key
		ENTER,
		// For Tab Key
		TAB,
		// For Shift Key
		SHIFT,
		// For Ctrl Key
		CTRL,
		// For Left Key
		LEFT,
		// For Right Key
		RIGHT,
		// For UP Key
		UP,
		// For Down Key
		DOWN,
		// To do Nothing
		None
	}

	public enum AppNameType {
		// For Web Applications
		Web,
		// For Windows Applications
		Windows
	}

	public enum LocatorName {
		// To locate XPATH
		XPATH,
		// To locate Name
		NAME,
		// To locate CSSSELECTOR
		CSSLOCATOR,
		// To locate ID
		ID,
		// To locate CLASSNAME
		CLASSNAME,
		// To locate LINKTEXT
		LINKTEXT,
		// To locate PARTIALLINKTEXT
		PARTIALLINKTEXT,
		// To locate TAGNAME
		TAGNAME,

	}

	public enum ApplicationType {
		// For Web Applications
		Web,
		// For Windows Applications
		Windows
	}

	public enum WaitTime {
		// To give a very Short wait
		VeryShortWait,
		// To give a Short Wait
		ShortWait,
		// To give a Medium Wait
		MediumWait,
		// To give a Long Wait
		LongWait

	}
}
