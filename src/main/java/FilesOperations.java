import java.io.File;
import java.util.Scanner;

public class FilesOperations {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input *.zip file to be moved: ");
        String zipFile = scanner.nextLine();
        System.out.print("Input new folder to move specified *.zip file: ");
        String newPath = scanner.nextLine();

        File file = moveFile(zipFile,newPath);

        if (file != null) {
            if (GZip.extract(newPath + File.separator + file.getName(), newPath + File.separator + "unzipped")) {
                SeekJavaFiles.seek(newPath + File.separator + "unzipped");
            }
        }
    }

    private static File moveFile(String zipFile, String newPath) {
        System.out.print("Moving src.zip file...");
        long time = System.currentTimeMillis();
        File file = new File(zipFile);
        if (file.isFile()) {
            if (file.renameTo(new File(newPath + File.separator + file.getName()))) {
                System.out.println("OK");
            } else {
                System.out.println("Failure");
            }
        } else {
            System.out.println(zipFile + " is not a file");
            return null;
        }
        time = System.currentTimeMillis() - time;
        System.out.println("Moving time: " + time / 1000.0 + "s");
        return file;
    }

}
