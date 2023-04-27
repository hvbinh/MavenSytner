package common;

public class GlobalConstants {

    //private static variable
    private static GlobalConstants globalConstants;

    //private constructor
    private GlobalConstants()
    {

    }

    //public static method
    public static synchronized GlobalConstants getGlobalConstants() {
        if(globalConstants == null)
        {
            globalConstants = new GlobalConstants();
        }
        return globalConstants;
    }
    private final long SHORT_TIME = 30;
    public long getSHORT_TIME() {
        return SHORT_TIME;
    }

    public long getLONG_TIME() {
        return LONG_TIME;
    }

    public String getROOT_FOLDER() {
        return ROOT_FOLDER;
    }

    public String getOS_NAME() {
        return OS_NAME;
    }

    public String getJAVA_VERSION() {
        return JAVA_VERSION;
    }

    private final long LONG_TIME = 60;
    private final String ROOT_FOLDER = System.getProperty("user.dir");
    private final String OS_NAME = System.getProperty("os.name");
    private final String JAVA_VERSION = System.getProperty("java.version");




    public static String getDirectorySlash(String folderName) {
        if (isMac() || isUnix() || isSolaris()) {
            folderName = "/" + folderName + "/";
        } else {
            folderName = "\\" + folderName + "\\";
        }
        return folderName;
    }

    public static boolean isWindows() {
        return (globalConstants.OS_NAME.toLowerCase().indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (globalConstants.OS_NAME.toLowerCase().indexOf("mac") >= 0);
    }

    public static boolean isUnix() {
        return (globalConstants.OS_NAME.toLowerCase().indexOf("nix") >= 0 || globalConstants.OS_NAME.toLowerCase().indexOf("nux") >= 0 || globalConstants.OS_NAME.toLowerCase().indexOf("aix") > 0);
    }

    public static boolean isSolaris() {
        return (globalConstants.OS_NAME.toLowerCase().indexOf("sunos") >= 0);
    }
}
