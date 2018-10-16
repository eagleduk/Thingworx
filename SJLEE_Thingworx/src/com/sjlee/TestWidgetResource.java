package com.SJLEE;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.json.JSONObject;
import org.slf4j.Logger;

import com.thingworx.contentmanagement.ImportAll;
import com.thingworx.entities.collections.RootEntityCollection;
import com.thingworx.entities.utils.ThingUtilities;
import com.thingworx.logging.LogUtilities;
import com.thingworx.metadata.FieldDefinition;
import com.thingworx.metadata.annotations.ThingworxServiceDefinition;
import com.thingworx.metadata.annotations.ThingworxServiceParameter;
import com.thingworx.metadata.annotations.ThingworxServiceResult;
import com.thingworx.metadata.collections.FieldDefinitionCollection;
import com.thingworx.relationships.RelationshipTypes.ThingworxRelationshipTypes;
import com.thingworx.resources.Resource;
import com.thingworx.security.context.SecurityContext;
import com.thingworx.system.entities.ThingWorxEntityManager;
import com.thingworx.system.managers.BaseManager;
import com.thingworx.system.managers.MashupManager;
import com.thingworx.system.managers.MediaEntityManager;
import com.thingworx.system.managers.StyleDefinitionManager;
import com.thingworx.system.managers.WidgetManager;
import com.thingworx.things.repository.FileRepositoryThing;
import com.thingworx.types.BaseTypes;
import com.thingworx.ux.mashups.Mashup;
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
				
				//FileInputStream fis = new FileInputStream(file);
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

	@SuppressWarnings("static-access")
	@ThingworxServiceDefinition(name = "ImportService", description = "", category = "", isAllowOverride = false, aspects = {
			"isAsync:false" })
	@ThingworxServiceResult(name = "result", description = "", baseType = "NOTHING", aspects = {})
	public void ImportService() {
		_logger.trace("Entering Service: ImportService");
		_logger.trace("Exiting Service: ImportService");
		
		try {

			String exportFileName = "C:/ThingworxStorage/repository/SystemRepository/import/Mashups_qwer123.xml";
			Boolean withData = false;
			Boolean withSubsystems = false;
			Boolean useDefaultDataProvider = true;
			SecurityContext securityContext = new SecurityContext();
			
			File ff = new File(exportFileName);
			
			if(ff.isFile()) {
				
				System.out.println("FFFF ============   " + ff.isFile());
				
				ImportAll a = new ImportAll(exportFileName, withData, securityContext, withSubsystems, useDefaultDataProvider);
				
				System.out.println(" ImportActive ====   " + a.isImportActive());
				
				a.run();
				
				System.out.println(" ImportActive ====   " + a.isImportActive());
			}
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@ThingworxServiceDefinition(name = "GetRepositoryFullPath", description = "", category = "", isAllowOverride = false, aspects = {
			"isAsync:false" })
	@ThingworxServiceResult(name = "result", description = "", baseType = "STRING", aspects = {})
	public String GetRepositoryFullPath(
			@ThingworxServiceParameter(name = "repositoryName", description = "", baseType = "THINGNAME") String repositoryName) throws Exception {
		_logger.trace("Entering Service: GetRepositoryFullPath");
		_logger.trace("Exiting Service: GetRepositoryFullPath");
		
		String  result = "";
		
		FileRepositoryThing repository = (FileRepositoryThing) ThingUtilities.findThing(repositoryName);
		
		result = repository.getRootPath();
		
		File file = new File(result);
		
		System.out.println("file.isDirectory() ==== " + file.isDirectory() + ", file.isFile() ====  " + file.isFile());
		
		return result;
	}

	@ThingworxServiceDefinition(name = "efefe", description = "", category = "", isAllowOverride = false, aspects = {
			"isAsync:true" })
	@ThingworxServiceResult(name = "Result", description = "", baseType = "NOTHING", aspects = {})
	public void efefe() {
		
		System.out.println("efefeefefeefefeefefeefefeefefeefefeefefeefefe");
		
		MashupManager mm = MashupManager.getInstance();
		Mashup mu = mm.getEntity("authTest");
		
		FieldDefinitionCollection fdc = mu.getParameters();
		/*
		Iterator<Entry<String, FieldDefinition>> ii = fdc.entrySet().iterator();
		while(ii.hasNext()) {
			Entry<String, FieldDefinition> ee = ii.next();
			
			FieldDefinition fd = ee.getValue();
			BaseTypes bt = fd.getBaseType();
			
			//System.out.println("name == " + ee.getKey() + ", baseType == " + bt + ", defaultValue == ");
			
		}
		*/
		
		List<String> nn = fdc.getNames();
		
		try {
			//System.out.println("JSON --- " + mu.toJSON().toString());
			JSONObject mashup = mu.toJSON();
			String content_S = mashup.getString("mashupContent");
			JSONObject content_J = new JSONObject(content_S);
			JSONObject ui_J = content_J.getJSONObject("UI");
			JSONObject properties_J = ui_J.getJSONObject("Properties");
			
			
			//System.out.println(" --- " + properties_J.toString());
			
			for(String name: nn) {
				System.out.println("before] name == " + name + ", value == " + properties_J.get(name));
				
				properties_J.put(name, name + "1");
				
				System.out.println("after] name == " + name + ", value == " + properties_J.get(name));
			}
			mu.setMashupContent(content_J.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@ThingworxServiceDefinition(name = "ChangeMashupParameterDefaultValue", description = "", category = "", isAllowOverride = false, aspects = {
			"isAsync:false" })
	@ThingworxServiceResult(name = "result", description = "", baseType = "NOTHING", aspects = {})
	public void ChangeMashupParameterDefaultValue(
			@ThingworxServiceParameter(name = "mashupName", description = "", baseType = "MASHUPNAME") String mashupName,
			@ThingworxServiceParameter(name = "propertyName", description = "", baseType = "STRING") String propertyName,
			@ThingworxServiceParameter(name = "defaultValue", description = "", baseType = "STRING") String defaultValue) {
		
		MashupManager mm = MashupManager.getInstance();
		Mashup mu = mm.getEntity(mashupName);
		
		try {

			JSONObject mashup = mu.toJSON();
			String content_S = mashup.getString("mashupContent");
			JSONObject content_J = new JSONObject(content_S);
			JSONObject ui_J = content_J.getJSONObject("UI");
			JSONObject properties_J = ui_J.getJSONObject("Properties");
			
			properties_J.put(propertyName, defaultValue);
			
			mu.setMashupContent(content_J.toString());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
