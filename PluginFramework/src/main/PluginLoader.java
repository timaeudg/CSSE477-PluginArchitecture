package main;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import javax.swing.JButton;

import framework.Plugin;

/**
 * This class monitors a selected directory and loads any plugin JARs added to
 * that directory into the list of loadPlugins.
 * 
 * @author harrissa
 * 
 */
public class PluginLoader implements Runnable {

	private Thread t;
	private String threadName = "pluginLoader";

	List<Plugin> loadPlugins;
	
	public List<Plugin> getLoadPlugins() {
		return loadPlugins;
	}
	
	public List<String> getLoadPluginsNames(){
		List<String> names = new ArrayList<String>();
		for(Plugin plug : this.loadPlugins) {
			names.add(plug.getClass().getSimpleName());
		}
		return names;
	}

	Path currentDir;
	boolean stopped = false;

	/**
	 * Defaults to DropJarsHere directory
	 */
	public PluginLoader() {
		this(Paths.get("./DropJarsHere/").toString());
	}

	/**
	 * If you for some reason already have a list of plugins, it instantiates
	 * with that list.
	 * 
	 * @param plugins
	 */
	public PluginLoader(String currentDir, List<Plugin> plugins) {
		loadPlugins = new ArrayList<>();
		setDir(currentDir);
		loadPlugins.addAll(plugins);

	};

	/**
	 * This constructor instantiates the list of plugins.
	 */
	public PluginLoader(String currentDir) {

		this(currentDir, new ArrayList<Plugin>());

	};

	/**
	 * Load jar is given a file path to the jar and it will extract the class
	 * that implements IPlugin and add it to the list.
	 * 
	 * @param filePath
	 */
	public void loadJar(String filePath) {

		try {
			JarFile jarFile = new JarFile(filePath);
			Enumeration<JarEntry> e = jarFile.entries();

			URL[] urls = { new URL("jar:file:" + filePath + "!/") };
			URLClassLoader cl = URLClassLoader.newInstance(urls);
			while (e.hasMoreElements()) {
				try {
					JarEntry je = (JarEntry) e.nextElement();
					if (je.isDirectory() || !je.getName().endsWith(".class")) {
						continue;
					}
					String className = je.getName().substring(0,
							je.getName().length() - 6);
					className = className.replace('/', '.');
					Class c = cl.loadClass(className);
					if(Plugin.class.isAssignableFrom(c)) {
						Constructor<Plugin> constructor = c.getConstructor();
						Plugin i = constructor.newInstance();
						this.loadPlugins.add(i);
					} else {
						continue;
					}

				} catch (ClassNotFoundException cnfe) {
					System.err.println("Class not found");
					cnfe.printStackTrace();
				} catch (NoSuchMethodException e1) {
					System.err.println("No such method");
					e1.printStackTrace();
				} catch (SecurityException e1) {
					System.err.println("Security Exception");
					e1.printStackTrace();
				} catch (InstantiationException e1) {
					System.err.println("Instantiation Exception");
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					System.err.println("IllegalAccessException");
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					System.err.println("IllegalArgumentException");
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					System.err.println("InvocationTargetException");
					e1.printStackTrace();
				}

			}

		} catch (IOException e) {
			System.err.println("Not a jarFile, no biggie. Moving on.");
		}

	}

	/**
	 * Watch dir monitors the current directory for any new files and calls
	 * loadJar on them.
	 */
	public void watchDir() {

		WatchService watcher = null;
		try {
			watcher = FileSystems.getDefault().newWatchService();
			WatchKey key = currentDir.register(watcher,
					StandardWatchEventKinds.ENTRY_CREATE);
		} catch (IOException x) {
			System.err.println(x);
		}

		while (true) {
			if (stopped) {
				break;
			}
			WatchKey key = null;
			try {
				key = watcher.take();

			} catch (InterruptedException x) {
				System.err.println("Interrupted Excetpion");
				x.printStackTrace();
			}

			for (WatchEvent<?> event : key.pollEvents()) {
				WatchEvent.Kind<?> kind = event.kind();

				if (kind == StandardWatchEventKinds.OVERFLOW) {
					continue;
				}

				WatchEvent<Path> ev = (WatchEvent<Path>) event;
				Path filename = ev.context();

				Path child = currentDir.resolve(filename);
				loadJar(child.toString());

				boolean valid = key.reset();
				if (!valid) {
					break;
				}
			}
		}

	}

	/**
	 * Clears the plugin list, loads the plugin jars from the new directory and
	 * resets the watcher to use the new directory.
	 * 
	 * @param dir
	 */
	public void setDir(String dir) {
		loadPlugins.clear();
		currentDir = Paths.get(dir);
		File currDir = currentDir.toFile();
		File[] listOfJars = currDir.listFiles();

		for (File jar : listOfJars) {

			loadJar(jar.toString());

		}
		reset();
	}

	/**
	 * Returns the directory currently loaded/watched.
	 * @return
	 */
	public String getDir() {

		return currentDir.toString();
	}

	/**
	 * For the threading.
	 */
	public void run() {
		watchDir();
	}

	/**
	 * For the threading.
	 */
	public void start() {
		if (t == null) {
			this.stopped = false;
			t = new Thread(this, threadName);
			t.start();

		}
	}

	/**
	 * To reset the threading.
	 */
	public void reset() {
		this.stopped = true;
		t = null;
		start();
	}

}
