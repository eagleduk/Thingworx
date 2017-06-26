TW.IDE.Widgets.test1 = function () {

	this.widgetIconUrl = function() {
		return  "'../Common/extensions/SJLEE_Thingworx/ui/test1/default_widget_icon.ide.png'";
	};

	this.widgetProperties = function () {
		return {
			'name': 'test1',
			'description': '',
			'category': ['Common'],
			'properties': {
				'test1 Property': {
					'baseType': 'STRING',
					'defaultValue': 'test1 Property default value',
					'isBindingTarget': true
				},
				'background_style': {
					 'baseType': 'STYLEDEFINITION',
	                 'defaultValue' : 'DefaultListItemStyle'
				},
				'style_List': {
					'baseType': 'STRING',
					'description': 'description',
					'selectOptions': [
						{value: 's', text: 'Series'},
						{value: 'x', text: 'xAxis'},
						{value: 'y', text: 'yAxis'},
					],
					'defaultValue': 's',
				},
				
				// Series Style
				'series_style': {
					'baseType': 'STYLEDEFINITION',
	                'defaultValue': 'DefaultListItemStyle',
	                'isVisible': false
				},
				'series_count': {
					'baseType': 'NUMBER',
					'defaultValue': 1,
					'isVisible': false
				},
				// XAxis Style
				'xAxis_style': {
					'baseType': 'STYLEDEFINITION',
	                'defaultValue' : 'DefaultListItemStyle',
	                'isVisible': false
				},
				// YAxis Style
				'yAxis_style': {
					'baseType': 'STYLEDEFINITION',
	                'defaultValue' : 'DefaultListItemStyle',
	                'isVisible': false
				}
			}
		}
	};

	this.afterSetProperty = function (name, value) {
		var thisWidget = this;
		var refreshHtml = false;
		switch (name) {
			case 'Style':
			case 'test1 Property':
				thisWidget.jqElement.find('.test1-property').text(value);
			case 'Alignment':
				refreshHtml = true;
				break;
			case 'background_style':
				var background_style = this.getProperty('background_style');
				
				var backgroundListItemResult = TW.getStyleFromStyleDefinition(background_style);
				
				var background_Color = TW.getBackgroundColor(backgroundListItemResult);
				
				refreshHtml = true;
			case 'style_List':
				var type = this.getProperty('style_List');
				
				var props = this.allWidgetProperties()['properties'];
				var seriesDataVisible = false;
				if(type === 's') {
					props['series_style']['isVisible']= true;
					props['series_count']['isVisible']= true;
					seriesDataVisible = true;
					
					props['xAxis_style']['isVisible']= false;
					props['yAxis_style']['isVisible']= false;
				}else if(type === 'x') {
					props['xAxis_style']['isVisible']= true;

					props['series_style']['isVisible']= false;
					props['series_count']['isVisible']= false;
					props['yAxis_style']['isVisible']= false;
				}else if(type === 'y') {
					props['yAxis_style']['isVisible']= true;

					props['series_style']['isVisible']= false;
					props['series_count']['isVisible']= false;
					props['xAxis_style']['isVisible']= false;
				}
				
				this.seriesDataIsVisible(props, seriesDataVisible);
				
				TW.IDE.updateWidgetPropertiesWindow();
				refreshHtml = true;
				break;
			case 'series_count': 
				var props = this.allWidgetProperties()['properties'];
				
				this.deleteSeriesData(props);
				this.addSeriesData(props);
				
				TW.IDE.updateWidgetPropertiesWindow();
				break;
			default:
				break;
		}
		return refreshHtml;
	};

	this.renderHtml = function () {
		// return any HTML you want rendered for your widget
		// If you want it to change depending on properties that the user
		// has set, you can use this.getProperty(propertyName).
		return 	'<div class="widget-content widget-test1">' +
					'<span class="test1-property">' + this.getProperty('test1 Property') + '</span>' +
				'</div>';
	};

	this.afterRender = function () {
		// NOTE: this.jqElement is the jquery reference to your html dom element
		// 		 that was returned in renderHtml()

		// get a reference to the value element
		valueElem = this.jqElement.find('.test1-property');
		// update that DOM element based on the property value that the user set
		// in the mashup builder
		valueElem.text(this.getProperty('test1 Property'));
		
		var type = this.getProperty('style_List');
		
		var props = this.allWidgetProperties()['properties'];
		var series_count = this.getProperty('series_count');
		var series_visible = false;
		
		if(type == 's') {
			props['series_style']['isVisible']= true;
			props['series_count']['isVisible']= true;
			
			props['xAxis_style']['isVisible']= false;
			props['yAxis_style']['isVisible']= false;
		}else if(type == 'x') {
			props['xAxis_style']['isVisible']= true;

			props['series_style']['isVisible']= false;
			props['series_count']['isVisible']= false;
			props['yAxis_style']['isVisible']= false;
		}else if(type == 'y') {
			props['yAxis_style']['isVisible']= true;

			props['series_style']['isVisible']= false;
			props['series_count']['isVisible']= false;
			props['xAxis_style']['isVisible']= false;
		}
	};
	
	this.deleteSeriesData = function(props) {
		console.log('===============   deleteSeriesData start  ========');
		for(name in props) {
			if(name.indexOf('seriesData_') >= 0) {
				delete props[name];
			}
		}
		console.log('===============   deleteSeriesData end  ========');
	};
	
	this.addSeriesData = function(props) {
		console.log('===============   addSeriesData start  ========');
		var series_count = this.getProperty('series_count');
		
		for(var i=0; i<series_count; i++) {
			var name = 'seriesData_' + (i+1);
			var baseType = 'STYLEDEFINITION';
			var defaultValue = 'DefaultListItemStyle';
			
			var newProperty = new Object();
			newProperty.name = name;
			newProperty.baseType = baseType;
			newProperty.defaultValue = defaultValue;
			newProperty.isVisible = true;
			newProperty.type = 'property';
			
			props[name] = newProperty;
		}
		console.log('===============   addSeriesData end  ========');
	};
	
	this.seriesDataIsVisible = function(props, isVisible) {
		console.log('===============   seriesDataIsVisible start  ========');
		var series_count = this.getProperty('series_count');
		for(var i=0; i<series_count; i++) {
			var name = 'seriesData_' + (i+1);
			console.log(name + " =====   " + props[name] );
			if(props[name] != undefined)
				props[name]['isVisible'] = isVisible;
		}
		console.log('===============   seriesDataIsVisible end  ========');
	};

};