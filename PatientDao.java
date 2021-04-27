import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PatientDao {
    HashMap<Integer, Patient> patients;
    OperationLogger operationsLogger;
    PatientDao(OperationLogger operationsLogger) {
        this.operationsLogger = operationsLogger;
        this.patients = new HashMap<>();
    }

    public Patient getPatientById(Integer id) {
        /*This function returns patients by the id*/
        return this.patients.get(id);
    }
    public void setPatientById(Integer id, Patient patient) throws IOException {
        /*This function sets patients by the id in a hashmap*/
        this.patients.put(id, patient);
        this.saveToFile();//we uploaded the patient.txt
    }

    public void removePatient(Integer id) throws IOException {
        //This function removes patient by id
        Patient patient = this.patients.get(id);
        this.operationsLogger.writeLine(("Patient "+id+" "+patient.getName() +" removed\n"));
        this.patients.remove(id);
        this.saveToFile();//we uploaded the patient.txt
    }

    public void loadFromFile(String path) throws IOException {
        /*This function for reading patient.txt and using necessary infos */
        List<String> lines = ReadFiles.readFile(path);
        for (String line : Objects.requireNonNull(lines)) {
            String[] infosArray = line.split("\t");
            String name = infosArray[1].split(" ")[0];
            String surname = infosArray[1].split(" ")[1];
            int id = Integer.parseInt(infosArray[0]);
            Patient patient = new Patient(id, name, surname, infosArray[2]/*phoneNumber*/, infosArray[3]/*address*/);
            this.setPatientById(id, patient);
        }
    }

    public void saveToFile() throws IOException {
        /*This function for uploading file when a change occurs*/
        try{
            FileWriter fw=new FileWriter("patient.txt");
            List<Patient> list=new ArrayList<Patient>(patients.values());
            Collections.sort(list, new Comparator<Patient>() {
                @Override
                public int compare(Patient o1, Patient o2) {
                    return o1.getId() - o2.getId();
                }
            });

            for (Patient patient : list) {
                fw.write(patient.getId()+"\t"+patient.getName()+" " +
                        patient.getSurname()+"\t"+patient.getPhoneNumber()
                        +"\t"+patient.getAddress()+"\n");}
            fw.close();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    public void addPatientIfNotExist(Integer id, Patient patient) throws IOException {
        /*This function adds new patient if their id not exist in the map*/
        if (!this.patients.containsKey(id)) {
            this.operationsLogger.writeLine("Patient "+ id +" "+patient.getName() +" added\n");
            this.patients.put(id, patient);
            this.saveToFile();//saves file
        }
    }

    public void printAllPatientsSorted() throws IOException {
        /*This function writes all patients on the output by sorting their name*/
        List<Patient> list=new ArrayList<Patient>(patients.values());
        Collections.sort(list, new Comparator<Patient>() {
            @Override
            public int compare(Patient o1, Patient o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        operationsLogger.writeLine("Patient List:\n");
        for (Patient patient : list) {
            operationsLogger.writeLine(patient.getId()+" "+patient.getName()+" " +
                    patient.getSurname()+" "+patient.getPhoneNumber()
                    +" "+patient.getAddress()+"\n");
        }

    }

}
