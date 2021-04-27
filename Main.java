public class Main {
    public static void main(String[] args) throws Exception {
        OperationLogger operationsLogger = new OperationLogger();//for writing outputs
        PatientDao patients = new PatientDao(operationsLogger);
        patients.loadFromFile("patient.txt");//reads patient file
        AdmissionDao admissions = new AdmissionDao(patients,operationsLogger);
        admissions.loadFromFile("admission.txt");// read admission file
        ReadFiles.readOperationInfos(operationsLogger, patients, admissions, args[0]);//reads inputs and use infos to do operations
    }
}