This project is a Java Full Stack Project, based on Java, Spring, Thymeleaf and Firebase. Firebase is used so that we can easily fetch the data and save the data rather than following the file methods.

NOTE: I was facing some issues while creating the packages, like Service Package, Domain Package, Controller Package and Configuration Package, So I did the development in one package only, while I am still trying to figure it out.

Steps I follwed.

1. Initiated the project from spring initalizr while adding the dependencies from the initalizr itself.
2. Run and check the server by creating the test @RestController.
3. Change to @RestController to @Controller, so that I can use Thymelaf.
4. Created to Models and Inject the thymeleaf dependency and created the view as per the requirement.
5. Home Page Html, contains two lists, one is all the static contacts, and the other one is where all the OTP calls are being made.
6. Seperate Contact Html is where u see the clicked row from the home page and next page where u see the randomly generated OTP to send message to the verified number.
7. For storing the OTP data, firestore is used. ComposeMessage class is used to store the firestore database.
8 Service class called MessageData is being used to process and fetch the data from the controller.

