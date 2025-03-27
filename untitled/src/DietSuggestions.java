public class DietSuggestions {
    PDFOpener opener = new PDFOpener();

    public void pdf(double bmi)
    {
        if (bmi < 18.5) {
            opener.openPdf("LT 18.5.pdf");
        } else if (bmi >= 18.5 && bmi < 22.9) {
            opener.openPdf("18.5 to 22.9.pdf");
        } else if (bmi >= 23.0 && bmi < 24.9) {
            opener.openPdf("23.5 to 24.9.pdf");
        } else if (bmi >= 25.0 && bmi < 29.9) {
            opener.openPdf("25 to 29.9.pdf");
        } else if (bmi >= 30.0 && bmi < 34.9) {
            opener.openPdf("30 to 34.9.pdf");
        } else if (bmi >= 35.0 && bmi < 39.9) {
            opener.openPdf("35 to 39.9.pdf");
        } else {
            System.out.println("No PDF available for this BMI range.");
        }

    }
}
