<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@page import="com.trainingprogram.model.RegisteredAccount"%>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Home</title>
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css"
    />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css
    "
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css"
    />
    <link rel="stylesheet" href="css/style.css" />
  </head>
  <body class="text-bg-dark">
      <jsp:include page="header.jsp"></jsp:include>
  
    <% RegisteredAccount ra = (RegisteredAccount)request.getAttribute("AccountInfo"); %>
    
    <section class="contaniner">
      <div
        class="row justify-content-center align-items-center"
        style="min-height: 80vh"
      >
        <div class="col-12 col-md-8 col-lg-4 text-center border p-4">
          <div>
            <h1>Welcome <br /><%= ra.getName() %></h1>
            <div class="d-flex flex-column gap-2">
              <a href="<%=request.getContextPath()%>/new?name=<%=ra.getName()%>&email=<%= ra.getEmail() %>" class="btn btn-dark">Register New Course</a>
              <a href="<%=request.getContextPath()%>/enrolledCourse?email=<%= ra.getEmail() %>" class="btn btn-dark">Show your course</a>
            </div>
          </div>
        </div>
      </div>
    </section>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js
    "></script>
    <script src="index.js"></script>
  </body>
</html>
