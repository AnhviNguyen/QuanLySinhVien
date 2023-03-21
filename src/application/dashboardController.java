package application;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java. time.LocalDate;
import java. util.ArrayList;
import java.util .Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class dashboardController implements Initializable{
    @FXML
    private BarChart<?, ?> home_TotalEnrolledChart;

    @FXML
    private TableColumn<studentData, String> addStudent_col_LastName;

    @FXML
    private Button addStudentBtn;

    @FXML
    private TextField available_Decription;

    @FXML
    private Button addStudent_DeleteBtn;

    @FXML
    private TableColumn<studentData, String> addStudent_col_Status;

    @FXML
    private TableView<studentData> addStudent_TableView;

    @FXML
    private Button addStudent_UpdateBtn;

    @FXML
    private Button addStudent_ClearBtn;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_Score;

    @FXML
    private Label home_TotalEnrolled;

    @FXML
    private TableColumn<studentData, String> addStudent_col_IDStudent; 

    @FXML
    private ComboBox<?> addStudent_Course;

    @FXML
    private TextField addStudent_IDStudent;

    @FXML
    private AnchorPane studentGrade_Form;

    @FXML
    private TableColumn<studentData, String> addStudent_col_FirstName;

    @FXML
    private AnchorPane addStudent_Form;

    @FXML
    private Button available_DeleteBtn;

    @FXML
    private TableColumn<courseData, String> available_col_Degree;

    @FXML
    private Button studentGrade_ClearBtn;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_IDStudent;

    @FXML
    private LineChart<?, ?> home_TotalMaleChart;

    @FXML
    private TextField addStudent_LastName;

    @FXML
    private ImageView addStudent_ImageView;

    @FXML
    private TextField addStudent_FirstName;

    @FXML
    private TextField studentGrade_MidScore;

    @FXML
    private Button gradeStudentBtn;

    @FXML
    private Button signOutBtn;

    @FXML
    private TextField available_Course;

    @FXML
    private TextField studentGrade_IDStudent;

    @FXML
    private TextField studentGrade_Search;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_MidScore;

    @FXML
    private TableView<studentData> studentGrade_TableView;

    @FXML
    private Button available_UpdateBtn;

    @FXML
    private Label home_TotalFemale;

    @FXML
    private AreaChart<?, ?> home_TotalFemaleChart;

    @FXML
    private TableColumn<courseData, String> available_col_Course;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_Rank;

    @FXML
    private TableColumn<courseData, String> available_col_Decription;

    @FXML
    private Button homeBtn;

    @FXML
    private Button close;

    @FXML
    private AnchorPane main_Form;

    @FXML
    private TextField addStudent_Search;

    @FXML
    private TableColumn<studentData, String> addStudent_col_BirthDay;

    @FXML
    private Button studentGrade_UpdateBtn;

    @FXML
    private DatePicker addStudent_BirthDate;

    @FXML
    private AnchorPane available_Form;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_FinalScore;

    @FXML
    private Button availableBtn;

    @FXML
    private TextField studentGrade_Course;

    @FXML
    private TableView<courseData> available_TableView;

    @FXML
    private AnchorPane home_Form;

    @FXML
    private TableColumn<studentData, String> addStudent_col_Course;

    @FXML
    private Button addStudent_AddBtn;

    @FXML
    private ComboBox<?> addStudent_Status;

    @FXML
    private TableColumn<studentData, String> addStudent_col_Gender;

    @FXML
    private TextField available_Degree;

    @FXML
    private Button available_AddBtn;

    @FXML
    private Button available_ClearBtn;

    @FXML
    private ComboBox<?> addStudent_Gender;

    @FXML
    private TextField studentGrade_FinalScore;

    @FXML
    private Button addStudent_InsertBtn;

    @FXML
    private TableColumn<studentData, String> studentGrade_col_Course;

    @FXML
    private Label home_TotalMale;

    @FXML
    private Label username;
    
    //session between java application and database" phien lam viec giua java aplication vs sql server
    private Connection connect;
    
    //used to execute dynamic or parameterized SQL queries: thuc hien cac cau truy van trong sql dong hoac co tham so (truyen duoc cac tham so trong khi chay : insert)
    private PreparedStatement prepare;
    
    //Provides methods to execute queries against SQL databases: cung cap cac phuong thuc de thuc thi cau lenh sql tinh (khong truyen tham so trong khi chay: create, alter, drop)
    private Statement statement;
    
    //a data table that represents the result set from the database that is returned by SQL commands" la mot bang du lieu sau khi thuc thi cac cau truy van
    private ResultSet result;
    
    private Image image;
    
    //Allows registering Listener to monitor when changes occur on the collection, such as: delete, add, edit elements: dung de dk listener trong tung cac element
    private ObservableList<studentData> addStudentListD;
    
    
    
    // hien thi tat ca so hoc sinh da dang ky
    public void homeDisplayTotalEnrolledStudent() {
    	String sql = "SELECT COUNT (IDStudent) AS count FROM student"; 
    	
    	connect = Database.connectDb();
    	
    	int countEnrolled = 0;
    	
    	try {
    		prepare = connect.prepareStatement(sql);
    		result= prepare.executeQuery();
    		
    		if (result.next()) {
    			countEnrolled = result.getInt("count");
    		}
    		
    		home_TotalEnrolled.setText(String.valueOf(countEnrolled));
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
    
   
    // hien thi tat ca so hoc sinh da dk co gioi tinh la nu
    public void homeDisplayFemaleEnrolled() {
    	String sql ="SELECT COUNT(IDStudent)AS count FROM student WHERE gender= 'Female' and statusStudent = 'Enrolled' ";
    	
    	connect = Database.connectDb();
    	
    	int countFemale =0;
    	
    	try {
    		prepare = connect.prepareStatement(sql);
    		result = prepare.executeQuery();
    		
    		if (result.next()) {
    			countFemale = result.getInt("count");
    		}
    		
    		home_TotalFemale.setText(String.valueOf(countFemale));
    		
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
    // hien thi tat ca so hoc sinh da dk co gioi tinh la nam
    public void homeDisplayMaleEnrolled() {
    	String sql = "SELECT COUNT (IDStudent)AS count FROM student WHERE gender= 'Male' and statusStudent = 'Enrolled' ";
    	
    	connect = Database.connectDb();
    	
    	try {
    		int countMale =0;
    		prepare = connect.prepareStatement(sql);
    		result = prepare.executeQuery();
    		
    		if (result.next()) {
    			countMale = result.getInt("count");
    		}
    		home_TotalMale.setText(String.valueOf(countMale));
    		
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
   // hien thi tat ca so hoc sinh da dk 
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void homeDisplayTotalEnrolledChart() {
    	home_TotalEnrolledChart.getData().clear();
    	
    	String sql = "SELECT dateStudent ,COUNT(IDStudent)  FROM student  WHERE  statusStudent = 'Enrolled' group by dateStudent ORDER BY dateStudent ASC ";
    	
    	connect = Database.connectDb();
    	
    	try {
    		XYChart.Series chart = new XYChart.Series();
	
    		
    		prepare = connect.prepareStatement(sql);
    		result = prepare.executeQuery();
    		
    		while (result.next()) {
    			chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
	
    		}
    		home_TotalEnrolledChart.getData().add(chart);
    		
    	}catch (Exception e) {
			e.printStackTrace();
		}  	
    }
    

    // hien thi tat ca so hoc sinh da dk co gioi tinh tren bieu do co gioi tinh la nu
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void homeDisplayTotalFemaleChart() {
    	home_TotalFemaleChart.getData().clear();
    	
    	String sql ="SELECT dateStudent ,COUNT(IDStudent)  FROM student  WHERE gender = 'Female' and statusStudent = 'Enrolled' group by dateStudent ORDER BY dateStudent  ASC ";
    	
    	connect = Database.connectDb();
    	
    	try {
			XYChart.Series chart = new XYChart.Series();
    		
    		prepare = connect.prepareStatement(sql);
    		result= prepare.executeQuery();
    		
    		while (result.next()) { 			
    			chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
    		
    		}
    		home_TotalFemaleChart.getData().add(chart);
    		
    	}catch (Exception e) {
			e.printStackTrace();
		} 	
    }
    /// hien thi tat ca so hoc sinh da dk co gioi tinh tren bieu do co gioi tinh la nam
    @SuppressWarnings({ "unchecked", "rawtypes" })
   	public void homeDisplayTotalMaleChart() {
    	home_TotalMaleChart.getData().clear();
       	
       	String sql ="SELECT dateStudent ,COUNT(IDStudent)  FROM student  WHERE gender = 'Male' and statusStudent = 'Enrolled' group by dateStudent ORDER BY dateStudent  ASC ";
       	
       	connect = Database.connectDb();
       	
       	try {
   			XYChart.Series chart = new XYChart.Series();
       		
       		prepare = connect.prepareStatement(sql);
       		result= prepare.executeQuery();
       		
       		while (result.next()) { 			
       			chart.getData().add(new XYChart.Data(result.getString(1), result.getInt(2)));
       		
       		}
       		home_TotalMaleChart.getData().add(chart);
       		
       	}catch (Exception e) {
   			e.printStackTrace();
   		} 	
       }
    
    // onAction trong button add o form add Student
    public void addStudentAdd() {
    	String insertData = "INSERT INTO student "
    			+"(IDStudent,FirstName,LastName,gender,birthDay,course,statusStudent,imageStudent,dateStudent)"
    			+"VALUES (?,?,?,?,?,?,?,?,?)";
    	
    	connect = Database.connectDb();
    	
    	try { 
    		Alert alert;
    		
    		if (addStudent_IDStudent.getText().isEmpty()
    				|| addStudent_Course.getSelectionModel().getSelectedItem()== null
    				|| addStudent_FirstName.getText().isEmpty() 
    				|| addStudent_LastName.getText().isEmpty()
    				|| addStudent_Gender.getSelectionModel().getSelectedItem()==null
    				|| addStudent_BirthDate.getValue()==null
    				|| addStudent_Status.getSelectionModel().getSelectedItem() ==null
    				) {
    			
    			alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Please fill all blank fields");
    			alert.showAndWait();	
    		}else {
    			
    			//check if the id student is already exist
    			String checkData ="SELECT IDStudent FROM student WHERE IDStudent = '"
    					+addStudent_IDStudent.getText() + "'";
    			
    			statement = connect.createStatement();
    			result =statement.executeQuery(checkData);
    			
    			if(result.next()) {
    				alert = new Alert(AlertType.ERROR);
        			alert.setTitle("Error Message");
        			alert.setHeaderText(null);
        			alert.setContentText("ID Student "+addStudent_IDStudent.getText()+ "  was already exist!" );
        			alert.showAndWait();
    			}else {
    				prepare = connect.prepareStatement(insertData);
        			prepare.setString(1, addStudent_IDStudent.getText());
        			prepare.setString(2, addStudent_FirstName.getText());
        			prepare.setString(3, addStudent_LastName.getText());
        			prepare.setString(4, (String)addStudent_Gender.getSelectionModel().getSelectedItem());
        			prepare.setString(5, String.valueOf(addStudent_BirthDate.getValue()));
        			prepare.setString(6, (String)addStudent_Course.getSelectionModel().getSelectedItem());
        			prepare.setString(7, (String)addStudent_Status.getSelectionModel().getSelectedItem());
        			
        			
        			String uri = getData.path;
        			uri = uri.replace("\\", "\\\\");
        			prepare.setString(8, uri);
        			
        			Date date = new Date();
        			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        			prepare.setString(9, String.valueOf(sqlDate));
        			// thuc hien update cai bang
        			prepare.executeUpdate();
        			
        			
        			
        			// them vao bang grade  student
        			String insertStudentGrade = "INSERT INTO  student_grade "
        					+"(IDStudent,course,midScore,finalScore,score,rank)"
        					+"VALUES(?,?,?,?,?,?)";
        			
        			prepare = connect.prepareStatement(insertStudentGrade);
        			prepare.setString(1, addStudent_IDStudent.getText());
        			prepare.setString(2, (String)addStudent_Course.getSelectionModel().getSelectedItem());
        			prepare.setString(3, "0.0");
        			prepare.setString(4, "0.0");
        			prepare.setString(5, "0.0");
        			prepare.setString(6, "");
        			
        			prepare.executeUpdate();
        			
        			alert = new Alert(AlertType.INFORMATION);
        			alert.setTitle("Information Message");
        			alert.setHeaderText(null);
        			alert.setContentText("Successfully Added!");
        			alert.showAndWait();	
        			
        			//to update the table view
        			addStudentShowListData();
        			addStudentSearch();
        			// to clear the fields
        			addStudentClear();
    			}	
    		}
	
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
	
    }
    
    
    // button update trong  addstudent
    public void addStudentUpdate() {
    	String uri = getData.path;
    	uri = uri.replace("\\", "\\\\");
    	
    	
    	
    	String updateData = "UPDATE student SET "
    			+"FirstName = '"+addStudent_FirstName.getText()
    			+"', LastName = '"+addStudent_LastName.getText()
    			+"', gender = '"+addStudent_Gender.getSelectionModel().getSelectedItem()
    			+"', birthDay ='"+addStudent_BirthDate.getValue()
    			+"', course ='"+addStudent_Course.getSelectionModel().getSelectedItem()
    			+"', statusStudent = '"+ addStudent_Status.getSelectionModel().getSelectedItem()
    			+"', imageStudent = '"+uri+"' WHERE IDStudent = '"
    			+addStudent_IDStudent.getText()+"'";
    	
    	connect = Database.connectDb();
    	
    	try {
    		Alert alert;
    		
    		if (addStudent_IDStudent.getText().isEmpty()
    				|| addStudent_Course.getSelectionModel().getSelectedItem()== null
    				|| addStudent_FirstName.getText().isEmpty() 
    				|| addStudent_LastName.getText().isEmpty()
    				|| addStudent_Gender.getSelectionModel().getSelectedItem()==null
    				|| addStudent_BirthDate.getValue()==null
    				|| addStudent_Status.getSelectionModel().getSelectedItem() ==null
    				|| getData.path == null  || getData.path == "") {
    			
    			alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Please fill all blank fields");
    			alert.showAndWait();	
    		}else {
    			alert = new Alert(AlertType.CONFIRMATION);
    			alert.setTitle("Confination Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Are you sure want to UPDATE ID student "+addStudent_IDStudent.getText()+" ?");
    			
    			// optional: kiem tra mot bien co ton tai hay khong
    			Optional<ButtonType> option = alert.showAndWait();
    			
    			if (option.get().equals(ButtonType.OK)) {
    				statement = connect.createStatement();
        			statement.executeUpdate(updateData);
        			
        			alert = new Alert(AlertType.INFORMATION);
        			alert.setTitle("Information Message");
        			alert.setHeaderText(null);
        			alert.setContentText("Successfully Update!");
        			alert.showAndWait();
        			
        			//to update the table view
        			addStudentShowListData();	
        			addStudentSearch();
        			// to clear the fields
        			addStudentClear();
        			
        			
    			}else return;	
    		}
    		
    	}catch (Exception e) {
			e.printStackTrace();
		}
    } 
    
    //nut delete trong add student
    public void addStudentDelete() {
    	
    	String deleteData = "DELETE FROM student WHERE IDStudent = '"
    			+addStudent_IDStudent.getText()+"'";
    	
    	connect = Database.connectDb();
    	
    	try {
    		Alert alert;
    		
    		if (addStudent_IDStudent.getText().isEmpty()
    				|| addStudent_Course.getSelectionModel().getSelectedItem()== null
    				|| addStudent_FirstName.getText().isEmpty() 
    				|| addStudent_LastName.getText().isEmpty()
    				|| addStudent_Gender.getSelectionModel().getSelectedItem()==null
    				|| addStudent_BirthDate.getValue()==null
    				|| addStudent_Status.getSelectionModel().getSelectedItem() ==null
    				|| getData.path == null  || getData.path == "") {
    			
    			alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Please fill all blank fields");
    			alert.showAndWait();	
    		}else {
    			alert = new Alert(AlertType.CONFIRMATION);
    			alert.setTitle("Confination Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Are you sure you want to DELETE ID Student " + addStudent_IDStudent.getText()+"?");
    			
    			Optional<ButtonType> option = alert.showAndWait();
    			
    			if (option.get().equals(ButtonType.OK)) {
    				statement = connect.createStatement();
    				statement.executeUpdate(deleteData);
    				
    				String CheckData = "SELECT IDStudent FROM student_grade"+
    						" WHERE IDStudent = '" +addStudent_IDStudent.getText()+"'";
    				
    				prepare = connect.prepareStatement(CheckData);
    				result= prepare.executeQuery();
    				
    				//if the student id is exist then process to delete
    				if ( result.next()) {
    					String deleteGrade = "DELETE FROM student_grade WHERE "
        						+ "IDStudent= '"+addStudent_IDStudent.getText()+"'";
    					
    					statement = connect.createStatement();
    					statement.executeUpdate(deleteGrade);
    				} // if not then 
    				
    				alert = new Alert(AlertType.INFORMATION);
        			alert.setTitle("Information Message");
        			alert.setHeaderText(null);
        			alert.setContentText("Successfully Delete!");
        			alert.showAndWait();
        			
        			//to update the table view
        			addStudentShowListData();	
        			addStudentSearch();
        			// to clear the fields
        			addStudentClear();
	
    			}else return;
    		}	
    	} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    
    // nut clear trong add student
    public void addStudentClear() {
    	addStudent_IDStudent.setText("");
    	addStudent_FirstName.setText("");
    	addStudent_LastName.setText("");
    	addStudent_Gender.getSelectionModel().clearSelection();
    	addStudent_BirthDate.setValue(null);
    	addStudent_Course.getSelectionModel().clearSelection();
    	addStudent_Status.getSelectionModel().clearSelection();
    	addStudent_ImageView.setImage(null);
    	
    	getData.path = "";
    }
    
    
    // insert dia chi image trong phan add student
    public void addStudentInsertImage() {
    	FileChooser open = new FileChooser();
    	open.setTitle("Open Image File");
    	open.getExtensionFilters().add(new ExtensionFilter("Image File","*jpg","*png"));
    	
    	File file = open.showOpenDialog(home_Form.getScene().getWindow());
    	
    	if (file != null) {
    		
    		image = new Image(file.toURI().toString(), 170, 179, false, true);
    		addStudent_ImageView.setImage(image);
    		
    		getData.path = file.getAbsolutePath();
    	}   	
    }
    
   
    
    // tao thong tin cho list view trong phan add student phan khoa hoc
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void addStudentCourseList() {
    	String listCourse = "SELECT * FROM course";
    	connect =Database.connectDb();
    	
    	try {
    		//ObservableList : tap hop cac doi tuong cho phep dang ky listener, FXCollections.observableArrayList();
			ObservableList list =FXCollections.observableArrayList();
    		
    		 prepare = connect.prepareStatement(listCourse);
    		 result =prepare.executeQuery();
    		 
    		 while (result.next()) {
    			 list.add(result.getString("Course"));
    		 }
    		 addStudent_Course.setItems(list);	
    	}catch (Exception e) {
			e.printStackTrace();		}
    	
    }
    
	//tao thong tin cho list view trong phan add student phan gioi tinh
    private String[] genderList = {"Male", "Female", "Others"};
    @SuppressWarnings("unchecked")
	public  void addStudentGenderList() {
    	List<String> gendereL = new ArrayList<>();
    	
    	for (String data: genderList ) {
    		gendereL.add(data);
    	}
    	@SuppressWarnings("rawtypes")
		ObservableList oblist = FXCollections.observableArrayList(gendereL);
    	addStudent_Gender.setItems(oblist);
    }
    
    // tao thong tin cho list view trong phan add student phan trang thai dang ki
    private String[] statusList = {"Enrolled", "Not Enrolled", "Inactive"};
    @SuppressWarnings("unchecked")
	public void addStudentStatusList() {
    	List<String> statusL = new ArrayList<>();
    	
    	for (String data: statusList ) {
    		statusL.add(data);
    	}
    	@SuppressWarnings("rawtypes")
		ObservableList oblist = FXCollections.observableArrayList(statusL);
    	addStudent_Status.setItems(oblist);
    }
    
    
    //lay tat ca thong tin da dang nhap tu csdl truoc do vao 1 bien ten studentD
    public ObservableList<studentData> addStudentListData(){
    	
    	ObservableList<studentData> list = FXCollections.observableArrayList();
    	
    	String sql ="SELECT * FROM student";
    	
    	connect = Database.connectDb();
    	
    	try {
    		studentData studentD;
    		prepare = connect.prepareStatement(sql);
    		result = prepare.executeQuery();
    		
    		while (result.next()) {
    			studentD = new studentData(
    					result.getInt("IDStudent"), 
    					result.getString("FirstName"), 
    					result.getString("LastName"), 
    					result.getString("gender"), 
    					result.getDate("birthDay"), 
    					result.getString("course"), 
    					result.getString("statusStudent"),  
    					result.getString("imageStudent"));
    					
    			list.add(studentD);
    			
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	return list;	
    }
    
   
    // tu nhung thong tin duoc luu va mang xuat ra man hinh duoi dang nhung dong trong bang
    public void addStudentShowListData() {
    	addStudentListD =addStudentListData();
    	
    	addStudent_col_IDStudent.setCellValueFactory(new PropertyValueFactory<>("IDStudent"));
    	addStudent_col_FirstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
    	addStudent_col_LastName.setCellValueFactory(new PropertyValueFactory<>("LastName")); 
    	addStudent_col_Gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
    	addStudent_col_BirthDay.setCellValueFactory(new PropertyValueFactory<>("birthDay"));
    	addStudent_col_Course.setCellValueFactory(new PropertyValueFactory<>("course"));
    	addStudent_col_Status.setCellValueFactory(new PropertyValueFactory<>("statusStudent"));
    	
    	addStudent_TableView.setItems(addStudentListD);
    }
    
    
 // nut tim kieme trong add student   
 public void addStudentSearch() {
    	
     FilteredList<studentData> filter = new FilteredList<>(addStudentListD, e -> true);

     addStudent_Search.textProperty().addListener((Observable, oldValue, newValue) -> {

         filter.setPredicate(predicateStudentData -> {

             if (newValue.isBlank() || newValue.isEmpty() || newValue == null) {
                 return true;
             }

             String searchKey = newValue.toLowerCase();

             if (predicateStudentData.getIDStudent().toString().indexOf(searchKey) > -1) {
                 return true;
             }else if (predicateStudentData.getCourse().toLowerCase().indexOf(searchKey) > -1) {
                 return true;
             } else if (predicateStudentData.getFirstName().toLowerCase().indexOf(searchKey) >-1) {
                 return true;
             } else if (predicateStudentData.getLastName().toLowerCase().indexOf(searchKey) >-1) {
                 return true;
             } else if (predicateStudentData.getGender().toLowerCase().indexOf(searchKey) >-1) {
                 return true;
             } else if (predicateStudentData.getBirthDay().toString().indexOf(searchKey) >-1) {
                 return true;
             } else if (predicateStudentData.getStatusStudent().toLowerCase().indexOf(searchKey) > -1) {
                 return true;
             } else {
                 return false;
             }
         });
     });

     SortedList<studentData> sortList = new SortedList<>(filter);

     sortList.comparatorProperty().bind(addStudent_TableView.comparatorProperty());
     
     addStudent_TableView.setItems(sortList);
    }
    
 
 // khi nhan vao bat cu dong nao tren bang thi se hien thi danh sach phia duoi
    public void addStudentSelect() {
    	studentData studentD = addStudent_TableView.getSelectionModel().getSelectedItem();
    	int num = addStudent_TableView.getSelectionModel().getSelectedIndex();
    	
    	if((num-1)< -1) return;
    	
    	addStudent_IDStudent.setText(String.valueOf(studentD.getIDStudent()));
    	addStudent_FirstName.setText(studentD.getFirstName());
    	addStudent_LastName.setText(studentD.getLastName());
    	addStudent_BirthDate.setValue(LocalDate.parse(String.valueOf(studentD.getBirthDay())));
    	
    	String uri = "file:"+studentD.getImageStudent();
    	
    	image = new Image(uri,170, 179 , false, true);
    	addStudent_ImageView.setImage(image);
    	
    	getData.path = studentD.getImageStudent();
    }
    
    
    // nut add trong available course
    public void availableCourseAdd() {
    	String insertData = "INSERT INTO course(Course,Decription,Degree) VALUES (?,?,?)";
    	
    	connect = Database.connectDb();
    	
    	try {
    		Alert alert;
    		
    		if (available_Course.getText().isEmpty() 
    				|| available_Decription  .getText().isEmpty() 
    				|| available_Degree.getText().isEmpty()) {
    			alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Please fill all blank fields");
    			alert.showAndWait();
    		}else {
    			String checkData = "SELECT Course FROM course WHERE Course = '"+available_Course.getText()+"'";
    			
    			statement =  connect.createStatement();
    			result = statement.executeQuery(checkData);
    			
    			if  (result.next()) {
    				alert = new Alert(AlertType.ERROR);
        			alert.setTitle("Error Message");
        			alert.setHeaderText(null);
        			alert.setContentText("Course: "+available_Course.getText()+" was already exist");
        			alert.showAndWait();
    			}else {
    				prepare = connect.prepareStatement(insertData);
    				prepare.setString(1, available_Course.getText());
    				prepare.setString(2, available_Decription.getText());
    				prepare.setString(3, available_Degree.getText());
    				
    				prepare.executeUpdate();
    				alert = new Alert(AlertType.INFORMATION);
        			alert.setTitle("Information Message");
        			alert.setHeaderText(null);
        			alert.setContentText("Successfully Added!");
        			alert.showAndWait();
        			
        			// to become update our table view once the data we gave
        			availableCourseShowListData();
        			
        			//to clear the text fields
        			availableCourseClear();
        			
        			
    			}
    		}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    // nut update trong available course
    public void availableCourseUpdate() {
    	String updateData = "UPDATE course SET Decription = '"
    				+available_Decription.getText()+"', Degree = '"
    				+available_Degree.getText()+"'WHERE Course = '"
    				+available_Course.getText()+"'";
    	
    	connect = Database.connectDb();
    	
    	try {
    		Alert alert ;
    		
    		if (available_Course.getText().isEmpty() 
    				|| available_Decription  .getText().isEmpty() 
    				|| available_Degree.getText().isEmpty()) {
    			alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Please fill all blank fields");
    			alert.showAndWait();
    		}else {
    			alert = new Alert(AlertType.CONFIRMATION);
    			alert.setTitle("Confination Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Are you sure want to Update Course: "+available_Course.getText()+"?");
    			Optional<ButtonType> option = alert.showAndWait();
    			
    			if (option.get().equals(ButtonType.OK)) {
    				statement = connect.createStatement();
        			statement.executeUpdate(updateData);
        			
        			alert = new Alert(AlertType.INFORMATION);
        			alert.setTitle("Information Message");
        			alert.setHeaderText(null);
        			alert.setContentText("Successfully Update!");
        			alert.showAndWait();
        			
        			// to become update our table view once the data we gave
        			availableCourseShowListData();
        			
        			//to clear the text fields
        			availableCourseClear();
        			
    			}else return;
    		}	
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    
    
    // nut xoa trong available course
    public void availableCourseDelete() {
    	String deleteData = "DELETE FROM course WHERE course = '"
    			+available_Course.getText()+"' ";
    	
    	connect = Database.connectDb();
    	
    	try {
    		Alert alert;
    		
    		if (available_Course.getText().isEmpty() 
    				|| available_Decription  .getText().isEmpty() 
    				|| available_Degree.getText().isEmpty()) {
    			alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Please fill all blank fields");
    			alert.showAndWait();
    		}else {
    			
    			alert = new Alert(AlertType.CONFIRMATION);
    			alert.setTitle("Confination Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Are you sure want to Delete Course: "+available_Course.getText()+"?");
    			Optional<ButtonType> option = alert.showAndWait();
    			
    			if (option.get().equals(ButtonType.OK)) {
    				statement = connect.createStatement();
        			statement.executeUpdate(deleteData);
        			
        			alert = new Alert(AlertType.INFORMATION);
        			alert.setTitle("Information Message");
        			alert.setHeaderText(null);
        			alert.setContentText("Successfully Delete!");
        			alert.showAndWait();
        			
        			// to become update our table view once the data we gave
        			availableCourseShowListData();
        			
        			//to clear the text fields
        			availableCourseClear();
        			
    			}else return;	
    		}
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    	
    	
    }
    // nut clear trong available course
    public void availableCourseClear() {
    	available_Course.setText("");
    	available_Decription.setText("");
    	available_Degree.setText("");
    }
    
    //lay du lieu trong bang course tu csdl vao list trong java
    public ObservableList<courseData> availableCourseData(){
    	ObservableList<courseData> list = FXCollections.observableArrayList();
    	
    	String sql = "SELECT * FROM course";
    	
    	 connect = Database.connectDb();
    	 
    	 try {
    		 courseData courseD;
    		 prepare = connect.prepareStatement(sql);
    		 result = prepare.executeQuery();
    		 
    		 while(result.next()) {
    			 courseD = new courseData(
    					 result.getString("Course"), 
    					 result.getString("Decription"), 
    					 result.getString("Degree"));
    			 
    			 list.add(courseD); 
    		 }
    		 
    	 }catch(Exception e) {
    		 e.printStackTrace();
    	 }
    	 return list; 
    }
    
    // hien thi list duoi dang thong tin cua bang
    public ObservableList<courseData> availableCourseList;
    public void availableCourseShowListData() {
    	
    	availableCourseList = availableCourseData();
    	
    	available_col_Course.setCellValueFactory(new PropertyValueFactory<>("Course"));
    	available_col_Decription.setCellValueFactory(new PropertyValueFactory<>("Decription"));
    	available_col_Degree.setCellValueFactory(new PropertyValueFactory<>("Degree"));
    	
    	available_TableView.setItems(availableCourseList);
    }
    
    //khi nhan vao bat cu dong nao cua bang thi se hien thi tren nhung text field
    public void availableCourseSelect() {
    	courseData courseD = available_TableView.getSelectionModel().getSelectedItem();
    	int num = available_TableView.getSelectionModel().getSelectedIndex();
    	
    	if ((num-1) <-1) {
    		return;
    	}
    	
    	available_Course.setText(courseD.getCourse());
    	available_Decription.setText(courseD.getDecription());
    	available_Degree.setText(courseD.getDegree());
    }
    
    //nut update trong student of grade
    public void studentGradeUpdate() {
    	
    	float finalCheck1 =0;
    	float finalCheck2 =0;
    	float finalResult =0;
    	String resultRank="";
    	
    	String checkData = "SELECT * FROM student_grade WHERE IDStudent = '"
    			+studentGrade_IDStudent.getText()+"'";
    	
    	connect = Database.connectDb();
    	
    	
    	try {
    		prepare = connect.prepareStatement(checkData);
    		result = prepare.executeQuery();
    		
    		if (result.next()) {
    			finalCheck1 = result.getFloat("midScore");
    			finalCheck2 = result.getFloat("finalScore");
    		}
    		
    		
    		// computing score
    		if (finalCheck1 ==0 || finalCheck2==0) {
    			finalResult=0;
    		}
    		else {
    			finalResult = ((Float.parseFloat(studentGrade_MidScore.getText())
    					+Float.parseFloat(studentGrade_FinalScore.getText()))/2);
    		}
    		
    		// computing rank
    		if ( finalResult<=10 && finalResult >=8.5) {
    			resultRank="A";
    		}else if (finalResult <8.5 && finalResult>=7.0) {
    			resultRank="B";
    		}else if (finalResult <7.0 && finalResult>=5.5) {
    			resultRank="C";
    		}else if (finalResult <5.5 && finalResult>=4.0) {
    			resultRank="D";
    		}else {
    			resultRank="F";
    		}
    		
    		
    		String updateData = "UPDATE student_grade SET "
        			+ " course = '" +studentGrade_Course.getText()
        			+ "', midScore = '" +studentGrade_MidScore.getText()
        			+ "', finalScore = '"+ studentGrade_FinalScore.getText()
        			+ "', score = '"+finalResult
        			+ "', rank = '"+resultRank+"' WHERE IDStudent = '"
        			+ studentGrade_IDStudent.getText()+"'";
    		
    		Alert alert;
    		
    		if (studentGrade_IDStudent.getText().isEmpty() 
    				|| studentGrade_Course.getText().isEmpty()) {
    			alert = new Alert(AlertType.ERROR);
    			alert.setTitle("Error Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Please fill all blank fields");
    			alert.showAndWait();
    		}else {
    			alert = new Alert(AlertType.CONFIRMATION);
    			alert.setTitle("Confination Message");
    			alert.setHeaderText(null);
    			alert.setContentText("Are you sure you want to Update ID Student "+studentGrade_IDStudent.getText()+"?");
    			Optional<ButtonType> option = alert.showAndWait();
    			
    			if (option.get().equals(ButtonType.OK)) {
    				statement = connect.createStatement();
            		statement.executeUpdate(updateData);
            		
            		alert = new Alert(AlertType.INFORMATION);
        			alert.setTitle("Information Message");
        			alert.setHeaderText(null);
        			alert.setContentText("Sucessfully Update!");
        			alert.showAndWait();
            		
            		// to update the table view
            		studentGradeShowListData();
            		// clear the text fields
            		studentGradeClear();
            		
            		
    			}else return;	
    		}	 	
    	}catch (Exception e) {
			e.printStackTrace();
		}	
    }
    
    // nut clear trong student of grade
    public void studentGradeClear() {
    	studentGrade_IDStudent.setText("");
    	studentGrade_Course.setText("");
    	studentGrade_MidScore.setText("");
    	studentGrade_FinalScore.setText("");
    }
    
    private double x=0;
    private double y=0;
    
    // nhan thong tin tu csdl bang student_grade ket vs vs jav thong qua lsitdata
    public ObservableList<studentData> studentGradeListData (){
    	
    	ObservableList<studentData> listData = FXCollections.observableArrayList();
    	
    	String sql = "SELECT * FROM  student_grade";
    	
    	connect = Database.connectDb();
    	
    	try {
    		studentData studentD ;
    		prepare= connect.prepareStatement(sql);
    		result = prepare.executeQuery();
    		
    		while (result.next()) {
    			studentD = new studentData(result.getInt("IDStudent")
    						, result.getString("course")
    						,result.getFloat("midScore")
    						, result.getFloat("finalScore")
    						, result.getFloat("score")
    						,result.getString("rank"));
    			
    			listData.add(studentD);
    		}

    	}catch (Exception e) {
			e.printStackTrace();
		}
    	return listData;

    }
    
    // nut search trong student of grade
    public void studentGradeSearch() {
    	 FilteredList<studentData> filter = new FilteredList<>(studentGradeList, e -> true);

         studentGrade_Search.textProperty().addListener((Observable, oldValue, newValue) -> {

             filter.setPredicate(predicateStudentData -> {

                 if (newValue.isEmpty() || newValue == null) {
                     return true;
                 }
                 String searchKey = newValue.toLowerCase();

                 if (predicateStudentData.getIDStudent().toString().contains(searchKey)) {
                     return true;
                 }  else if (predicateStudentData.getCourse().toLowerCase().contains(searchKey)) {
                     return true;
                 } else if (predicateStudentData.getFinalScore().toString().contains(searchKey)) {
                     return true;
                 } else if (predicateStudentData.getMidScore().toString().contains(searchKey)) {
                     return true;
                 } else if (predicateStudentData.getScore().toString().contains(searchKey)) {
                     return true;
                 }else if (predicateStudentData.getRank().toLowerCase().contains(searchKey)) {
                     return true;
                 } else {
                     return false;
                 }
             });
         });

         SortedList<studentData> sortList = new SortedList<>(filter);

         sortList.comparatorProperty().bind(studentGrade_TableView.comparatorProperty());
         studentGrade_TableView.setItems(sortList);
    }
    
    // hien thi thong tin duoc lay tu listdata vao bang
    private ObservableList<studentData> studentGradeList;
    
    public void studentGradeShowListData() {
    	
    	studentGradeList = studentGradeListData();
    	
    	studentGrade_col_IDStudent.setCellValueFactory(new PropertyValueFactory<>("IDStudent"));
    	studentGrade_col_Course.setCellValueFactory(new PropertyValueFactory<>("course"));
    	studentGrade_col_MidScore.setCellValueFactory(new PropertyValueFactory<>("midScore"));
    	studentGrade_col_FinalScore.setCellValueFactory(new PropertyValueFactory<>("finalScore"));
    	studentGrade_col_Score.setCellValueFactory(new PropertyValueFactory<>("score"));
    	studentGrade_col_Rank.setCellValueFactory(new PropertyValueFactory<>("rank"));
    	
    	studentGrade_TableView.setItems(studentGradeList);
    }
    
    
    // khi nhan vao bang se hien thi nhung thong tin trong text field
    public void studentGradeSelect() {
    	
    	studentData studentD = studentGrade_TableView.getSelectionModel().getSelectedItem();
    	int num = studentGrade_TableView.getSelectionModel().getSelectedIndex();
    	
    	if ((num-1)<-1) {
    		return;
    	}
    	
    	studentGrade_IDStudent.setText(String.valueOf(studentD.getIDStudent()));
    	studentGrade_Course.setText(studentD.getCourse());
    	studentGrade_MidScore.setText(String.valueOf(studentD.getMidScore()));
    	studentGrade_FinalScore.setText(String.valueOf(studentD.getFinalScore()));
    	
    }
    

    // nut logout 
    public void logout() {
    	try {
    		
    		Alert alert = new Alert(AlertType.CONFIRMATION);
    		alert.setTitle("Confination Message");
    		alert.setHeaderText(null);
    		alert.setContentText("Are you sure wwant to logout ?");
    		
    		Optional<ButtonType> option = alert.showAndWait();
    		
    		if (option.get().equals(ButtonType.OK)) {
    			
    			
    			// link login form
    			Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
    			Stage stage = new Stage();
    			Scene scene = new Scene(root);
    			
    			root.setOnMousePressed((MouseEvent event) -> {
    				x= event.getSceneX();
    				y= event.getSceneY();
    			});
    			
    			root.setOnMouseDragged((MouseEvent event) -> {
    				stage.setX(event.getScreenX()-x);
    				stage.setY(event.getScreenY()-y);
    				stage.setOpacity(.8);
    			});
    			
    			root.setOnMouseReleased((MouseEvent event) -> {
    				stage.setOpacity(1);
    			});
    			
    			stage.initStyle(StageStyle.TRANSPARENT);
    			
    			stage.setScene(scene);
    			stage.show();	
    		}else {
    			return;
    		}	
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    // hien thi ten 
    public void displayName() {
    	username.setText(getData.username);
	}
    
    
    // style khi nhan vao nut button home
    public void defaultNav() {
    	homeBtn.setStyle("-fx-background-color: linear-gradient(to bottom right,#8957b8,#5f239a );");
    }
    
    // swtich the section in the menu bar
    public void switch_form(ActionEvent event ) {
    	if (event.getSource()== homeBtn) {
    		home_Form.setVisible(true);
    		addStudent_Form.setVisible(false);
    		available_Form.setVisible(false);
    		studentGrade_Form.setVisible(false);
    		
    		homeBtn.setStyle("-fx-background-color: linear-gradient(to bottom right,#8957b8,#5f239a );");
    		addStudentBtn.setStyle("-fx-background-color: transparent");
    		availableBtn.setStyle("-fx-background-color: transparent");
    		gradeStudentBtn.setStyle("-fx-background-color: transparent");
    		
    		 homeDisplayTotalEnrolledStudent();
    		 homeDisplayFemaleEnrolled();
    		 homeDisplayMaleEnrolled();
    		 homeDisplayTotalEnrolledChart();
    		 homeDisplayTotalFemaleChart();
    		 homeDisplayTotalMaleChart();
    		
    	}
    	else if (event.getSource()== addStudentBtn) {
    		home_Form.setVisible(false);
    		addStudent_Form.setVisible(true);
    		available_Form.setVisible(false);
    		studentGrade_Form.setVisible(false);
    		
    		addStudentBtn.setStyle("-fx-background-color: linear-gradient(to bottom right,#8957b8,#5f239a );");
    		homeBtn.setStyle("-fx-background-color: transparent");
    		availableBtn.setStyle("-fx-background-color: transparent");
    		gradeStudentBtn.setStyle("-fx-background-color: transparent");
    		
    		addStudentShowListData();
    		addStudentGenderList();
    		addStudentStatusList();
    		addStudentCourseList();
    		addStudentSearch();
    		
    	}
    	else if (event.getSource()== availableBtn) {
    		home_Form.setVisible(false);
    		addStudent_Form.setVisible(false);
    		available_Form.setVisible(true);
    		studentGrade_Form.setVisible(false);
    		
    		availableBtn.setStyle("-fx-background-color: linear-gradient(to bottom right,#8957b8,#5f239a );");
    		homeBtn.setStyle("-fx-background-color: transparent");
    		gradeStudentBtn.setStyle("-fx-background-color: transparent");
    		addStudentBtn.setStyle("-fx-background-color: transparent");
    		
    		availableCourseShowListData();
    	}
    	else if (event.getSource()==gradeStudentBtn){
    		home_Form.setVisible(false);
    		addStudent_Form.setVisible(false);
    		available_Form.setVisible(false);
    		studentGrade_Form.setVisible(true);
    		
    		gradeStudentBtn.setStyle("-fx-background-color: linear-gradient(to bottom right,#8957b8,#5f239a );");
    		homeBtn.setStyle("-fx-background-color: transparent");
    		availableBtn.setStyle("-fx-background-color: transparent");
    		addStudentBtn.setStyle("-fx-background-color: transparent");
    		
    		studentGradeShowListData();
    		studentGradeSearch();
    	}
    }
    

    
    public void close() {
    	System.exit(0);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		displayName();
		defaultNav() ;
		
		 homeDisplayTotalEnrolledStudent();
		 homeDisplayFemaleEnrolled();
		 homeDisplayMaleEnrolled();
		 homeDisplayTotalEnrolledChart();
		 homeDisplayTotalFemaleChart();
		 homeDisplayTotalMaleChart();
		 
		//to show imediately after opening the dashboard
		addStudentShowListData();
		addStudentGenderList();
		addStudentStatusList();
		addStudentCourseList();
		addStudentSearch();

		
		availableCourseShowListData();
		
		studentGradeShowListData();
		studentGradeSearch();
	}
}
