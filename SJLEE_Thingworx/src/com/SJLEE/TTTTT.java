package com.SJLEE;

import com.thingworx.logging.LogUtilities;
import com.thingworx.metadata.annotations.ThingworxServiceDefinition;
import com.thingworx.metadata.annotations.ThingworxServiceResult;
import org.slf4j.Logger;

public class TTTTT {

	private static Logger _logger = LogUtilities.getInstance().getApplicationLogger(TTTTT.class);

	public TTTTT() {
		// TODO Auto-generated constructor stub
	}

	@ThingworxServiceDefinition(name = "setName", description = "", category = "", isAllowOverride = false, aspects = {
			"isAsync:false" })
	@ThingworxServiceResult(name = "Result", description = "", baseType = "NOTHING", aspects = {})
	public void setName() {
		_logger.trace("Entering Service: setName");
		_logger.trace("Exiting Service: setName");
	}

}
