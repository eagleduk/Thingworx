TW.IDE.Widgets.test2 = function () {

	this.widgetIconUrl = function() {
		return  "'../Common/extensions/SJLEE_Thingworx/ui/test2/default_widget_icon.ide.png'";
	};

	this.widgetProperties = function () {
		return {
			'name': 'test2',
			'description': '',
			'category': ['Common'],
			'properties': {
				'test2 Property': {
					'baseType': 'STRING',
					'defaultValue': 'test2 Property default value',
					'isBindingTarget': true
				}
			}
		}
	};

	this.afterSetProperty = function (name, value) {
		var thisWidget = this;
		var refreshHtml = false;
		switch (name) {
			case 'Style':
			case 'test2 Property':
				thisWidget.jqElement.find('.test2-property').text(value);
			case 'Alignment':
				refreshHtml = true;
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
		return 	'<div class="widget-content widget-test2">' +
					'<span class="test2-property">' + this.getProperty('test2 Property') + '</span>' +
				'</div>';
	};

	this.afterRender = function () {
		// NOTE: this.jqElement is the jquery reference to your html dom element
		// 		 that was returned in renderHtml()

		// get a reference to the value element
		valueElem = this.jqElement.find('.test2-property');
		// update that DOM element based on the property value that the user set
		// in the mashup builder
		valueElem.text(this.getProperty('test2 Property'));
		
		this.test2 = this.jqElement.common();
		this.test2.styleTest();
	};
};