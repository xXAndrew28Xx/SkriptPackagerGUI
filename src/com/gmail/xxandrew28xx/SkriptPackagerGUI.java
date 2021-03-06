package com.gmail.xxandrew28xx;

import java.io.File;
import java.net.URL;
import java.net.URLDecoder;

import javax.swing.JFrame;

import com.gmail.xxandrew28xx.frames.AddonSelectionFrame;
import com.gmail.xxandrew28xx.frames.ClaimJarFrame;
import com.gmail.xxandrew28xx.frames.CompileFrame;
import com.gmail.xxandrew28xx.frames.MainFrame;
import com.gmail.xxandrew28xx.frames.SkriptDependencySelectionFrame;
import com.gmail.xxandrew28xx.frames.SkriptOptionsFrame;

public class SkriptPackagerGUI{
	/*
	 * USES SP.ZIP (Skript Packager 0.0.1-SNAPSHOT)
	 */
	public static JFrame visible;
	
	public static MainSkript ms;
	
	public static void main(String[] args) {
		try{
			String path = SkriptPackagerGUI.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			if (path.endsWith(".jar")){
				path = path.substring(0, path.lastIndexOf("/") + 1);
				path += "/";
			}
			String decodedPath = URLDecoder.decode(path, "UTF-8");
			setCurrentDirectory(decodedPath);
			System.out.println(System.getProperty("user.dir"));
		}catch(Exception e){
			e.printStackTrace();
		}
		MainFrame mf = new MainFrame();
		mf.setVisible(true);
		visible = mf;
	}
	public static boolean setCurrentDirectory(String directory_name)
    {
        boolean result = false;  // Boolean indicating whether directory was set
        File    directory;       // Desired current working directory

        directory = new File(directory_name).getAbsoluteFile();
        if (directory.exists() || directory.mkdirs())
        {
            result = (System.setProperty("user.dir", directory.getAbsolutePath()) != null);
        }

        return result;
    }
	public static void next(){
		visible.setVisible(false);
		if (visible instanceof MainFrame){
			visible = new SkriptOptionsFrame();
		}else if (visible instanceof SkriptOptionsFrame){
			visible = new AddonSelectionFrame();
		}else if (visible instanceof AddonSelectionFrame){
			visible = new SkriptDependencySelectionFrame();
		}else if (visible instanceof SkriptDependencySelectionFrame){
			visible = new CompileFrame();
		}else if (visible instanceof CompileFrame){
			visible = new ClaimJarFrame();
		}
		
		visible.setVisible(true);
	}
	public static class MainSkript{
		private String name;
		private String author;
		private String version;
		private String description;
		private Boolean hidden;
		private File file;
		public MainSkript(String name, String author, String version, String description, Boolean hidden, File file){
			this.name = name;
			this.author = author;
			this.version = version;
			this.description = description;
			this.hidden = hidden;
			this.file = file;
		}
		
		
		public String getName() {
			return name;
		}
		@Override
		public String toString(){
			return name + " " + author + " " + version + " " + description + " " + hidden + " " + file.getAbsolutePath();
			
		}
		
		public void setName(String name) {
			this.name = name;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getVersion() {
			return version;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
		
		public Boolean getHidden(){
			return hidden;
		}
		public void setHidden(Boolean hidden){
			this.hidden = hidden;
		}
		public File getFile(){
			return file;
		}
		public void setFile(File file){
			this.file = file;
		}

	}
	public static class Addon{
		public VersionToDL download;
		String name;
		public Addon(VersionToDL download, String name){
			this.download = download;
			this.name = name;
		}
		public VersionToDL getDownload(){
			return download;
		}
		public String getName(){
			return name;
		}
	}
	public static interface VersionToDL{
		URL fromVersion(String version);
	}
	

}
