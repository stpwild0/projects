<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->

<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>Hello!</title>
  </head>
  <body>
  <FORM action="/location" method="post">
    <TEXTAREA name="content" rows="20" cols="80"><location>
<longitude>23.421</longitude>
<latitude>113.32</latitude>
<accuracy>32.00000</accuracy>
<time><%= System.currentTimeMillis() %></time>
</location></TEXTAREA>
    <br>
    <INPUT type="submit" value="Send">
  </FORM>
  </body>
</html>