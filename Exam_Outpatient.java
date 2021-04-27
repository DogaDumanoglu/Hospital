public class Exam_Outpatient implements ExaminationInterface {
    ExaminationDecorator operations;

    Exam_Outpatient(ExaminationDecorator e) {
        this.operations = e;
    }

    @Override
    public void addOperation(ExaminationDecorator operations) {
        /*This function adds operations then calls cost function*/
        ((ExaminationInterface) operations).cost();
    }

    @Override
    public int cost(){
        /*This function works as a part of decorator pattern for calculating the cost*/
            return 15 + (operations != null ? operations.cost() : 0);
        }

    public String toString() {
        /*This function works part of decorator pattern for explanation of operation"*/
        String output = "Outpatient ";
        if (operations != null) {
            output += operations.toString();
        }
        return output;
    }

    public String toStringWithTab() {
        /*This function works part of decorator pattern for explanation of operation"*/
        String output = "Outpatient\t";
        if (operations != null) {
            output += operations.toString();
        }
        return output;
    }
}


