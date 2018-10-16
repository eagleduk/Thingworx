package com.SJLEE;

import java.util.Iterator;
import java.util.Map.Entry;

import org.slf4j.Logger;

import com.thingworx.entities.utils.ThingUtilities;
import com.thingworx.logging.LogUtilities;
import com.thingworx.metadata.annotations.ThingworxServiceDefinition;
import com.thingworx.metadata.annotations.ThingworxServiceParameter;
import com.thingworx.metadata.annotations.ThingworxServiceResult;
import com.thingworx.resources.Resource;
import com.thingworx.things.Thing;
import com.thingworx.types.InfoTable;
import com.thingworx.types.collections.ValueCollection;
import com.thingworx.types.primitives.IPrimitiveType;

@SuppressWarnings("serial")
public class ServiceTestResource extends Resource {

	private static Logger _logger = LogUtilities.getInstance().getApplicationLogger(ServiceTestResource.class);

	public ServiceTestResource() {
		// TODO Auto-generated constructor stub
	}

	@ThingworxServiceDefinition(name = "GetServiceList", description = "", category = "", isAllowOverride = false, aspects = {
			"isAsync:false" })
	@ThingworxServiceResult(name = "result", description = "", baseType = "NOTHING", aspects = {})
	public void GetServiceList(
			@ThingworxServiceParameter(name = "thingName", description = "", baseType = "THINGNAME") String thingName) throws Exception {
		_logger.trace("Entering Service: GetServiceList");
		_logger.trace("Exiting Service: GetServiceList");
		
		System.out.println("*******************************    " + thingName + "   ****************************************");
		
		Thing thing = ThingUtilities.findThing(thingName);
		
		InfoTable services = thing.GetServiceDefinitions(null, null, null);
		
		int serviceLength = services.getRowCount();
		
		for(int i=0; i<serviceLength; i++) {
			ValueCollection vc = services.getRow(i);
			
			System.out.println("NAME : " + vc.get("name"));
			
		}
		
		
	}

}
