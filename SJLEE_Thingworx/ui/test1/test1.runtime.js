TW.Runtime.Widgets.test1= function () {
	var valueElem;
	this.renderHtml = function () {
		// return any HTML you want rendered for your widget
		// If you want it to change depending on properties that the user
		// has set, you can use this.getProperty(propertyName). In
		// this example, we'll just return static HTML
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
	};

	// this is called on your widget anytime bound data changes
	this.updateProperty = function (updatePropertyInfo) {
		// TargetProperty tells you which of your bound properties changed
		console.log(this.getProperty('test1 Property'));
		if (updatePropertyInfo.TargetProperty === 'test1 Property') {
			valueElem.text(updatePropertyInfo.SinglePropertyValue);
			this.setProperty('test1 Property', updatePropertyInfo.SinglePropertyValue);
		}
	};
};