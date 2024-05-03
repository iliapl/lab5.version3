package util;

public class EnvDoing {
        public String getPATHcollection() {
        /*
        System.out.println("Выберите переменную окружения");
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        String path = null;
        if(java.lang.System.getProperty(name) != null) {
           path =  System.getenv(name);
        }
       else {
           getPATHcollection();
        }

         */
            return System.getenv("VehicleCollection");
        }

}
