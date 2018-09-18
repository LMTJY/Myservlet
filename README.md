# myservlet
#### servlet注册方式：

```
@WebServlet(name="WelcomeServlet", urlPatterns="/welcome")
```

#### 登录操作

```
String username = req.getParameter("username");
		String userpassword = req.getParameter("userpassword");
		System.out.println(username +"   "+ userpassword);
		//登录验证....
		//....
		
		//设置登录成功的标志
		req.getSession().setAttribute("logined", "yes");
		
		Cookie unameCookie = new Cookie("username",  URLEncoder.encode(username));
		unameCookie.setMaxAge(60*60);//秒为单位，
		resp.addCookie(unameCookie);//写cookie到浏览器中
		
		resp.sendRedirect("welcome");//  浏览器访问的相对地址 
```

#### 登出操作

```
		req.getSession().invalidate();//session作废了
		resp.sendRedirect("login");
```

#### json操作

```
		private Gson gson = new Gson();
		Employee employee = new Employee(1, "Karan", "IT", 5000);//创建对象
        String employeeJsonString = this.gson.toJson(employee);//转化成字符串
 
        PrintWriter out = resp.getWriter();
        //text/html
        resp.setContentType("application/json");//设置MIME类型
        resp.setCharacterEncoding("UTF-8");
        out.print(employeeJsonString);//把内容发送到浏览器
        out.flush();   
```

#### 接受用户发送来的json字符串

```
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		BufferedReader reader = req.getReader();
		Employee emp = gson.fromJson(reader, Employee.class);
		System.out.print(emp);
	}
```

#### servlet上传操作

```
//获取上传文件的名字
private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return null;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// getServletContext().getRealPath("")获取全局路径
		String uploadPath = getServletContext().getRealPath("") + "upload";
		System.out.println("uploadPath = " + uploadPath);
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists())
            uploadDir.mkdir();

        try {
            String fileName = "";
            for (Part part : req.getParts()) {
                fileName = getFileName(part);//获取文件名称
                if(fileName==null||fileName=="") continue;
                part.write(uploadPath + File.separator + fileName);//把part写入到文件
            }
            //反馈文件写入成功
            req.setAttribute("message", "File " + fileName + " has uploaded successfully!");
        } catch (FileNotFoundException fne) {
            req.setAttribute("message", "There was an error: " + fne.getMessage());
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/result.jsp").forward(req, resp);
		
	}
```

#### serlvet下载操作

```
String fileName = req.getParameter("filename");
		System.out.println(fileName);
		//resp.setContentType("text/plain");
        resp.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO_8859_1") );
		try(	InputStream in = req.getServletContext().getResourceAsStream("/WEB-INF/download/"+fileName);
				OutputStream out = resp.getOutputStream()){
			byte[] buffer = new byte[ARBITARY_SIZE];
			int numBytesRead;
			while((numBytesRead = in.read(buffer))>0){
				out.write(buffer, 0, numBytesRead);
			}
		}
```

#### 多文件下载

```
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {		
		String downloadPath = getServletContext().getRealPath("/");
		//File.separator  Windows表示“\”  Linux表示“/”
		downloadPath = downloadPath + "WEB-INF" + File.separator + "download" + File.separator;
		System.out.println(downloadPath);
		File file = new File(downloadPath);//创建文件夹
		File[] files;
		if(file.isDirectory()){
			files = file.listFiles();//列出文件夹中的内容
			req.setAttribute("files",files);
		}
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/list-download.jsp");
		dispatcher.forward(req, resp);
	}
```

#### 设置初始化参数

```
@WebServlet(name="UserServlet", urlPatterns="/user", initParams={
		@WebInitParam(name="name", value="not provided"),
		@WebInitParam(name="email", value="not provided")
})
```

```
${pageContext.request.contextPath}//获取项目名称
```

