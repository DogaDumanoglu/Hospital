public class Operation_DoctorVisit extends ExaminationDecorator {
    public Operation_DoctorVisit(ExaminationDecorator d) {
        this.cost = 15;/*Part of decorator pattern for calculating the cost*/
        this.next = d;
    }

    public String toString() {
        /*This function works part of decorator pattern for explanation of operation"*/
        String output = "doctorvisit ";
        if (next != null) {
            output += next.toString();
        }
        return output;
    }

}
