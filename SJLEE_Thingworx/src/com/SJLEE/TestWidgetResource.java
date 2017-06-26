package com.SJLEE;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

import org.json.JSONObject;
import org.slf4j.Logger;

import com.thingworx.entities.collections.RootEntityCollection;
import com.thingworx.logging.LogUtilities;
import com.thingworx.metadata.annotations.ThingworxServiceDefinition;
import com.thingworx.metadata.annotations.ThingworxServiceParameter;
import com.thingworx.metadata.annotations.ThingworxServiceResult;
import com.thingworx.relationships.RelationshipTypes.ThingworxRelationshipTypes;
import com.thingworx.resources.Resource;
import com.thingworx.system.entities.ThingWorxEntityManager;
import com.thingworx.system.managers.BaseManager;
import com.thingworx.system.managers.MediaEntityManager;
import com.thingworx.system.managers.StyleDefinitionManager;
import com.thingworx.system.managers.WidgetManager;
import com.thingworx.ux.media.MediaEntity;
import com.thingworx.ux.styles.StyleDefinition;
import com.thingworx.ux.widgets.Widget;

public class TestWidgetResource extends Resource {

	private static Logger _logger = LogUtilities.getInstance().getApplicationLogger(TestWidgetResource.class);

	private String[] checkEntityType = {"StyleDefinition", "MediaEntity"};
	
	
	public TestWidgetResource() {
		// TODO Auto-generated constructor stub
	}

	@ThingworxServiceDefinition(name = "ChangeWidgetStyles", description = "", category = "Widget", isAllowOverride = false, aspects = {
			"isAsync:false" })
	@ThingworxServiceResult(name = "result", description = "", baseType = "NOTHING", aspects = {})
	public void ChangeWidgetStyles() throws Exception {
		_logger.trace("Entering Service: ChangeWidgetStyles");
		_logger.trace("Exiting Service: ChangeWidgetStyles");
		
		StyleDefinitionManager s = StyleDefinitionManager.getInstance();
		StyleDefinition ss = s.getEntity("test1111");
		
		JSONObject sJSON = ss.toJSON();
		JSONObject content = sJSON.getJSONObject("content");
		
		String backgroundColor = content.getString("backgroundColor");
		String foregroundColor = content.getString("foregroundColor");
		String lineColor = content.getString("lineColor");
		String secondaryBackgroundColor = content.getString("secondaryBackgroundColor");
		
		System.out.println("backgroundColor =============  " + backgroundColor);
		System.out.println("foregroundColor =============  " + foregroundColor);
		System.out.println("lineColor ===================  " + lineColor);
		System.out.println("secondaryBackgroundColor ====  " + secondaryBackgroundColor);
		
		
		
	}

	@ThingworxServiceDefinition(name = "ChangeColors", description = "", category = "Widget", isAllowOverride = false, aspects = {
			"isAsync:false" })
	@ThingworxServiceResult(name = "result", description = "", baseType = "NOTHING", aspects = {})
	public void ChangeColors(
			@ThingworxServiceParameter(name = "background", description = "", baseType = "STRING") String background,
			@ThingworxServiceParameter(name = "foreground", description = "", baseType = "STRING") String foreground,
			@ThingworxServiceParameter(name = "line", description = "", baseType = "STRING") String line,
			@ThingworxServiceParameter(name = "secondary", description = "", baseType = "STRING") String secondary) throws Exception {
		_logger.trace("Entering Service: ChangeColors");
		_logger.trace("Exiting Service: ChangeColors");
		
		StyleDefinitionManager s = StyleDefinitionManager.getInstance();
		StyleDefinition ss = s.getEntity("test1111");
		
		JSONObject sJSON = ss.toJSON();
		JSONObject content = sJSON.getJSONObject("content");
		
		String backgroundColor = content.getString("backgroundColor");
		String foregroundColor = content.getString("foregroundColor");
		String lineColor = content.getString("lineColor");
		String secondaryBackgroundColor = content.getString("secondaryBackgroundColor");
		
		//System.out.println("backgroundColor =============  " + backgroundColor);
		//System.out.println("foregroundColor =============  " + foregroundColor);
		//System.out.println("lineColor ===================  " + lineColor);
		//System.out.println("secondaryBackgroundColor ====  " + secondaryBackgroundColor);
		
		if(background.length() > 0 && !background.isEmpty()) {
			background = "#" + background;
		}
		if(foreground.length() > 0 && !foreground.isEmpty()) {
			foreground = "#" + foreground;
		}
		if(line.length() > 0 && !line.isEmpty()) {
			line = "#" + line;
		}
		if(secondary.length() > 0 && !secondary.isEmpty()) {
			secondary = "#" + secondary;
		}
		content.put("backgroundColor", background);
		content.put("foregroundColor", foreground);
		content.put("lineColor", line);
		content.put("secondaryBackgroundColor", secondary);
		
		System.out.println("content ===  " + content);
		
		ss.setJSONContent(content);
		
	}

	@ThingworxServiceDefinition(name = "EntryTest", description = "", category = "", isAllowOverride = false, aspects = {
			"isAsync:false" })
	@ThingworxServiceResult(name = "result", description = "", baseType = "NOTHING", aspects = {})
	public void EntryTest() {
		_logger.trace("Entering Service: EntryTest");
		_logger.trace("Exiting Service: EntryTest");
		
		try {

			// All Entity
			ThingWorxEntityManager entities = ThingWorxEntityManager.getInstance();
			
			// Style Definition Entity
			ThingworxRelationshipTypes type = ThingworxRelationshipTypes.StyleDefinition;
			
			// Get Style Definition Entities
			BaseManager bm = entities.getEntityManagerByEntityType(type);
			
			// Get All Style Definition Entities
			RootEntityCollection rc = bm.getEntityCollection();
			
			Iterator<?> it = rc.keySet().iterator();
			
			while(it.hasNext()) {
				String name = it.next().toString();
				
				StyleDefinitionManager s = StyleDefinitionManager.getInstance();
				StyleDefinition ss = s.getEntity(name);
				
				JSONObject sJSON = ss.toJSON();
				JSONObject content = sJSON.getJSONObject("content");
				
				String backgroundColor = content.getString("backgroundColor");
				
				System.out.println(name + ", background == " + backgroundColor);
			}
			
			/*
			Iterator<BaseManager> it = entities.getEntityManagers().values().iterator();
			
			while(it.hasNext()){
				BaseManager bm = (BaseManager) it.next();
				
				System.out.println("Name ==  " + it.toString() + ", Type == " + bm.getEntityType() + ", toString ==  " + bm.toString());
				
				System.out.println("==================== " +  bm.getSkeletonEntityAsJSON());
				
			}
			*/
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@ThingworxServiceDefinition(name = "GetWidgetInfomations", description = "", category = "", isAllowOverride = false, aspects = {
			"isAsync:false" })
	@ThingworxServiceResult(name = "result", description = "", baseType = "NOTHING", aspects = {})
	public void GetWidgetInfomations() {
		_logger.trace("Entering Service: GetWidgetInfomations");
		_logger.trace("Exiting Service: GetWidgetInfomations");
		
		try {
			
			// All Entity
			ThingWorxEntityManager entities = ThingWorxEntityManager.getInstance();
			
			// Style Definition Entity
			ThingworxRelationshipTypes type = ThingworxRelationshipTypes.Widget;
			
			// Get Style Definition Entities
			BaseManager bm = entities.getEntityManagerByEntityType(type);
			
			// Get All Style Definition Entities
			RootEntityCollection rc = bm.getEntityCollection();
			
			Iterator<?> it = rc.keySet().iterator();
			
			while(it.hasNext()) {
				String name = it.next().toString();
				
				WidgetManager manager = WidgetManager.getInstance();
				Widget widget = manager.getEntity(name);
				
				JSONObject object = widget.toJSON();
				
				System.out.println(name + " ++++   " + object );
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@ThingworxServiceDefinition(name = "ChangeImageTest", description = "", category = "", isAllowOverride = false, aspects = {
			"isAsync:false" })
	@ThingworxServiceResult(name = "result", description = "", baseType = "NOTHING", aspects = {})
	public void ChangeImageTest(
			@ThingworxServiceParameter(name = "path", description = "", baseType = "STRING") String path) throws Exception {
		_logger.trace("Entering Service: ChangeImageTest");
		_logger.trace("Exiting Service: ChangeImageTest");

		Connection con = null;
		DBConnect instance = null;

		ResultSet rs = null;
		PreparedStatement st = null;
		
		try {
			
			MediaEntityManager manager = MediaEntityManager.getInstance();
			MediaEntity entity = manager.getEntity("testmedia11");
			
			JSONObject json = entity.toJSON();
			
			//System.out.println("json ....   " + json);
			
			//System.out.println(json.get("content").getClass() + " +++ \n content == " + json.get("content"));
			
			File file = new File(path);
			
			//System.out.println("file exists == " + file.isFile());
			
			Path p = Paths.get(path);
			byte[] data = Files.readAllBytes(p);
			
			//JSONObject sJSON = entity.toJSON();
			//JSONObject cc = sJSON.getJSONObject("content");
			
			//entity.SetAvatar(data);
			
			if(file.isFile()) {
				
				instance = DBConnect.getInstance();
				con = instance.getConnection("HHI_DATABASE");
				
				con.setAutoCommit(false);
				
				StringBuffer sb = new StringBuffer();
				
				sb.append("UPDATE mediaentity_model ");
				sb.append("SET content = ? ,");
				//sb.append("avatar = ?, ");
				sb.append("description = ? ");
				sb.append("WHERE ");
				sb.append("name = ? ");
				
				st = con.prepareStatement(sb.toString());
				
				FileInputStream fis = new FileInputStream(file);
				//st.setBinaryStream(1, fis, (int)file.length());
				st.setBytes(1, data);
				//st.setBytes(2, data);
				
				//System.out.println("data ==  " + data.toString());
				
				//System.out.println("length == " + (int)file.length());
				st.setString(2, path);
				st.setString(3, "testmedia11");
				st.executeUpdate();
				
				con.commit();
				
				
			}else {
				throw new Exception("Not is File");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			DBConnect.closeDBConnection(rs, st);
		}
		
	}
	
}
