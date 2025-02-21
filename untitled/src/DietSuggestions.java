public class DietSuggestions {
    PDFOpener opener = new PDFOpener();

    public void pdf(double bmi)
    {
        if(bmi < 18.5)
        {
            opener.openPdf("LT 18.5.pdf");
        }

    }
}
