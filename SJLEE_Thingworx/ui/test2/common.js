(function($, window, document) {

	var pluginName = "common", dataKey = "plugin_" + pluginName;

	var Plugin = function(element, options) {

		this.element = element;
		this.init(options);

	};
	Plugin.prototype = {
		init : function(option) {
			$.extend(this.options, option);
		},
		styleTest: function() {
			console.log("document == ", document);
			
			console.log("this == ", this);
			
			console.log("TW.jqPlugins == ", TW.jqPlugins);
			
			console.log("TW.jqPlugins.twStyleDefinitionEditor == ", TW.jqPlugins.twStyleDefinitionEditor);
			
			console.log("TW.jqPlugins.twStyleDefinitionEditor() == ", TW.jqPlugins.twStyleDefinitionEditor("twStyleDefinitionEditor"));
			
		},
	};
		
	$.fn[pluginName] = function(options) {

		var plugin = this.data(dataKey);

		if (plugin instanceof Plugin) {
			if (typeof options !== 'undefined') {
				plugin.init(options);
			}
		} else {
			plugin = new Plugin(this, options);
			this.data(dataKey, plugin);
		}

		return plugin;
	};

}(jQuery, window, document));

