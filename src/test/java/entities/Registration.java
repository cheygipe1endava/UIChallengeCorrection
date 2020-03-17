package entities;

public class Registration
{
    private String name;
    private String firstLastName;
    private String secondLastName;
    private String email;
    private String password;
    private String confirmPassword;
    private String country;
    private String documentType;
    private String documentNum;
    private String day;
    private String month;
    private String year;
    private String cellphone;

    public Registration(String list_id, String status_code, String success, String created_by, String item_present)
    {
        this.name = list_id;
        this.firstLastName = status_code;
        this.secondLastName = success;
        this.email = created_by;
        this.password = item_present;
        this.confirmPassword = status_code;
        this.country = success;
        this.documentType = created_by;
        this.documentNum = item_present;
        this.day = status_code;
        this.month = success;
        this.year = created_by;
        this.cellphone = item_present;
    }

    /*
    public String getList_id() {return list_id;}
    public String getStatus_code() {return status_code;}
    public String getSuccess() {return success;}
    public String getCreated_by() {return created_by;}
    public String getItem_present() {return item_present;}

     */
}
