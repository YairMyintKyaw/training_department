<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="com.trainingprogram.model.Course"%>
<%@page import="java.lang.Math"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Training Department | Register</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css
    " />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
<link rel="stylesheet" href="css/style.css" />
</head>
<body class="text-bg-dark">
	<jsp:include page="header.jsp"></jsp:include>

	<!-- registeration section -->
	<section class="container p-md-3 ">
		<div
			class="row align-items-center overflow-hidden  justify-content-lg-between justify-content-center ">
			<!-- register form -->
			<div
				class="col-12 col-md-8 col-lg-5 animate__animated animate__fadeInLeft mb-3 mb-lg-0">
				<div class="border shadow  p-4 px-5 rounded m-auto"
					style="max-width: 500px">
					<h3 class="text-center mb-4 fs-2">Registration Form</h3>
					<form action="insert"
						class="d-flex flex-column align-content-around ">
						<input required type="hidden" class="form-control" id="name"
							name="name" placeholder="Ye Myint Kyaw"
							value=<%=request.getAttribute("name")%>> 
						<input required
							type="hidden" class="form-control" name="email" id="email"
							placeholder="example@gmail.com" 
							value=<%= request.getAttribute("email") %>>
							
						<div class="mb-3">
							<label for="courses" class="form-label">Selected Course</label> <select
								name="course" id="courses" class="form-select">
								<%
								List<Course> course = (List<Course>) request.getAttribute("listCourse");
								for (Course i : course) {
								%>
								<option value="<%=i.getCourseName()%>">
									<%=i.getCourseName()%>(<%=(int) i.getPrice()%>mmk)
								</option>
								<%
								}
								%>
							</select>
						</div>
						<div class="mb-3">
							<label for="time" class="form-label">Selected Time</label> <select
								name="time" id="time" class="form-select">
								<%
								List<String> timeList = (List<String>) request.getAttribute("time");
								for (String i : timeList) {
								%>
								<option value="<%=i%>">
									<%=i%>
								</option>
								<%
								}
								%>
							</select>
						</div>
						<input required type="submit"
							class="btn btn-outline-light fs-5 px-3 w-75 align-self-center shadow"
							value="Register">

					</form>
				</div>
			</div>
			<div class="col-lg-1"></div>
			<div
				class="col-12 col-md-8 col-lg-6 animate__animated animate__fadeInRight">
				<img src="images/PngItem_3345756.png" class="img-fluid" alt="" />
			</div>
		</div>
	</section>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js
    "></script>
	<script src="index.js"></script>
</body>
</html>
