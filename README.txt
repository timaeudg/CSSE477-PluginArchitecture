# CSSE477-PluginArchitecture
Doge Plugin Architecture

The Doge Plugin architecture is a generic plugin architecture for use with Java & Swing

When running Platform program, a window will display that has 4 main features:

1) An Empty Panel near the middle
2) A Text Area near the bottom (this is the Status area to resemble a console)
3) A List area near the left which will list the available plugins to load
4) A directory box with a load button near the top which displays the directory to look for plugins


The platform constantly monitors the directory that is shown at the top, and any time a JAR file with a plugin
is added, it will automatically loaded. However, the controls will also allow for the user to select a different
directory to monitor and load from by pressing the button and using the File Dialogue to navigate to their desired
directory. From there, the list of plugins that are loaded into the platform will be on the left side, with the
plugin's class name. In order to load the plugin, simply press the "Load" button at the bottom of the list with
the desired plugin highlighted. From there, the platform will stop the currently running plugin, clear the execution
area, and then start the new plugin with access to the execution area and the "console".

In order to implement a Plugin, new projects must extend the Plugin abstract class, and implement the methods that
allow for starting and stopping of the plugin.