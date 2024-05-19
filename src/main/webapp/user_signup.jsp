<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Signup Page</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://unpkg.com/gijgo@1.9.14/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.14/css/gijgo.min.css" rel="stylesheet" type="text/css" /> 
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="Index.jsp">Docalendar</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse flex flex-row-reverse" id="navbarNav">
            <ul class="navbar-nav"> 
                <li class="nav-item"> 
                    <a class="nav-link" href="./Index.jsp">Log in</a>
                </li> 
            </ul>
        </div>
    </nav>

    <div class="container py-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        Signup
                    </div>
                    <div class="card-body">
                        <form action="register" method="post">
                            <div class="form-group">
                                <label for="fname">Full name</label>
                                <input type="text" class="form-control" id="fname" name="username" required>
                            </div>
                            <div class="form-group">
                                <label for="email">Email address</label>
                                <input type="email" class="form-control" id="email" name="email" required>
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" class="form-control" id="password" name="password" required>
                            </div>
                            <div class="form-group">
                                <label for="confirmPassword">Confirm Password</label>
                                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" required>
                            </div>
                            <div class="form-group">
                                <label for="exampleInputEmail1">Select Appointment type</label>
                                <select  class="form-control" id="typeOfAppointment" name="typeOfAppointment">
                                    <option value="general_checkup:James Olson">General Checkup ( James Olson )</option>
                                    <option value="follow_up_checkup:Clark Kent">Followup Checkup ( Clark Kent )</option>
                                    <option value="specialized_checkup:Lois Lane">Specialized Checkup ( Lois Lane )</option>
                                    <option value="annual_checkup:Cory Powers">Annual Checkup ( Cory Powers )</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="typeOfAppointment">Appointment date</label>
                                <input id="datepicker" name=appointment_date min="YYYY-MM-DD" max="YYYY-MM-DD"/>
                                <script>
                                    $('#datepicker').datepicker({
                                        uiLibrary: 'bootstrap5'
                                    });
                                </script>
                            </div> 
                            <div class="form-group">
                              <label for="">Note for your doctor</label>
                              <textarea name="note" id="note" class="form-control" style=resize:none></textarea> 
                            </div> 

                            <a href="./Index.jsp" class="d-block mb-3">already have an account? Log in</a>
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script>
        $(document).ready(function() {
            $('body').bootstrapMaterialDesign();
            
            $('.datetimepicker').datetimepicker({
            	pattern="yyyy-MM-dd"
                format: 'YYYY-MM-DD HH:mm',
                icons: {
                    time: "fa fa-clock-o",
                    date: "fa fa-calendar",
                    up: "fa fa-chevron-up",
                    down: "fa fa-chevron-down",
                    previous: 'fa fa-chevron-left',
                    next: 'fa fa-chevron-right',
                    today: 'fa fa-screenshot',
                    clear: 'fa fa-trash',
                    close: 'fa fa-remove'
                }
            });
        });
    </script> 

    <!-- Include Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>    
</body>
</html>