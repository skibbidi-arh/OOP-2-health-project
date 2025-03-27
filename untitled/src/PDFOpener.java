import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class PDFOpener {
    public  void openPdf(String fileName) {




        File file = new File(fileName);


        if (Desktop.isDesktopSupported() && file.exists()) {
            try {
                Desktop.getDesktop().open(file);
                System.out.println("PDF opened successfully.");
            } catch (IOException e) {
                System.out.println("Error opening PDF: " + e.getMessage());
            }
        } else {
            System.out.println("Desktop is not supported or file not found.");
        }
    }
}
