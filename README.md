# Task-Management-API

# Overview
The Task Management API is designed to facilitate the management of tasks within an application. It provides endpoints for creating, retrieving, updating, and deleting tasks, as well as additional features such as user authentication, role-based access control, and task filtering and sorting. This overview includes a focus on the key components and features of the application, with an emphasis on the incorporation of unit testing.

Technologies Used
* Java
* Spring Boot
* JPA (Java Persistence API)
* Hibernate
* PostgreSQL
* Maven
* Lombok

# Controller

UserController Endpoints

1. Sign Up:
    * Endpoint: POST /user/SignUp
    * Description: Registers a new user with email, password, and token. If the token is "ADMIN_ACCESS," the user is assigned the ADMIN role; otherwise, they receive the USER role.
    * Request Parameters: Email, Password, Token
    * Response: Returns a User1 object on successful registration.

2. Login:
    * Endpoint: GET /user/login
    * Description: Allows a user to log in with email and password. If the user does not exist, a UserNotExistException is thrown. If the password is incorrect, a WrongCredentialException is thrown.
    * Request Parameters: Email, Password
    * Response: Returns a User1 object on successful login or a GeneralMessageDTO with an error message.

TaskController Endpoints

1. Create Task:
    * Endpoint: POST /task/CreateTask
    * Description: Creates a new task in the database for an authenticated user.
    * Request Body: NewTaskDTO
    * Request Parameter: Email (User's email)
    * Response: Returns a Task object on successful creation, or a GeneralMessageDTO with an error message if the user does not exist.

2. Task List:
    * Endpoint: GET /task/TaskList
    * Description: Retrieves a list of tasks for an authenticated user or an admin user.
    * Request Parameter: Email (User's email)
    * Response: Returns a list of tasks or a GeneralMessageDTO with an error message if the user does not exist or lacks access.
  
3. Update Task:
    * Endpoint: POST /task/updateTask
    * Description: Updates task details and changes the status of a task (e.g., pending, in progress, completed).
    * Request Body: TaskDTO
    * Request Parameter: Status (e.g., pending, In Progress, completed)
    * Response: Returns the updated task or a GeneralMessageDTO with an error message if the task or user does not exist.

4. Delete Task:
    * Endpoint: DELETE /task/deleteTask
    * Description: Deletes a task for an authenticated or admin user.
    * Request Parameter: TaskId (Task identifier), Email (User's email)
    * Response: Returns a result message or a GeneralMessageDTO with an error message if the task or user does not exist or lacks access.

5. Filtering and Sorting:
    * Endpoint: GET /task/FilteringAndSorting
    * Description: Retrieves a paginated list of tasks with optional filtering and sorting.
    * Request Parameters: title (task title), page (page number), size (page size), sortList (list of sorting fields), sortOrder (sorting order)
    * Response: Returns a Page<Task> object based on the specified criteria.
  
  
# Service

UserService Methods

1. SignUp:
    * Description: Registers a new user based on email, password, and token.
    * Parameters: Email, Password, Token
    * Returns: User1 object.

2. Login:
    * Description: Authenticates a user based on email and password.
    * Parameters: Email, Password
    * Returns: User1 object.

TaskService Methods

1. createNewTask:
    * Description: Creates a new task and associates it with the specified user.
    * Parameters: NewTaskDTO, email
    * Returns: Task object.

2. getAllTaskList:
    * Description: Retrieves a list of all tasks, accessible only to admin users.
    * Parameters: Email
    * Returns: List of Task objects.

3. updateTaskDetails:
    * Description: Updates the details and status of a task.
    * Parameters: TaskDTO, Status
    * Returns: Updated Task object.

4. deleteTask:
    * Description: Deletes a task, accessible to the task owner or admin user.
    * Parameters: TaskId, Email
    * Returns: Result message.

5. getTaskListByFilteringAndSorting:
    * Description: Retrieves a paginated list of tasks with optional filtering and sorting.
    * Parameters: title, page, size, sortList, sortOrder
    * Returns: Page<Task> object.

# Repository

UserRepository Methods

1. getUserByEmail:
    * Description: Retrieves a user based on email.
    * Parameters: Email
    * Returns: User1 object.

2. getUserByUserId:
    * Description: Retrieves a user based on user ID.
    * Parameters: userId
    * Returns: User1 object.

4. insertUser_Task_List:
    * Description: Inserts a record into the user-task mapping table.
    * Parameters: userId, taskId

5. pdateUser_Task_List:
    * Description: Updates the user-task mapping table with new associations.
    * Parameters: userId, taskId

6. getUserByTaskId:
    * Description: Retrieves the user associated with a specific task.
    * Parameters: taskId
    * Returns: User1 object.

7. delteFromUserTaskList:
    * Description: Deletes records from the user-task mapping table.
    * Parameters: taskId

8. isPresent:
    * Description: Checks if a task is associated with any user.
    * Parameters: taskId
   
TaskRepository Methods

1. getTaskById:
    * Description: Retrieves a task based on task ID.
    * Parameters: taskId
    * Returns: Task object or null if not found.

2. getTaskListFilteringByTitle:
    * Description: Retrieves a paginated list of tasks filtered by title.
    * Parameters: title(Title for filtering), pageable(Pageable object for pagination, sorting, and filtering)
    * Returns: Paginated list of tasks.

