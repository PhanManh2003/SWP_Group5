CREATE DATABASE [OCMS3]
GO
USE [OCMS3]
GO
/****** Object:  Table [dbo].[Admin]    Script Date: 10/31/2024 10:32:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Admin](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](100) NOT NULL,
	[email] [varchar](200) NOT NULL,
	[phone] [nvarchar](12) NOT NULL,
	[password] [nvarchar](100) NOT NULL,
	[status] [int] NOT NULL,
	[avatar] [nvarchar](500) NULL,
 CONSTRAINT [PK_Admin] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Assignments]    Script Date: 10/31/2024 10:32:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Assignments](
	[AssignmentID] [int] IDENTITY(1,1) NOT NULL,
	[Title] [nvarchar](100) NULL,
	[Description] [nvarchar](max) NULL,
	[DueDate] [datetime] NULL,
	[ClassID] [int] NULL,
	[Type] [int] NULL,
	[Status] [int] NULL,
 CONSTRAINT [PK__Assignme__32499E57ED54ECDC] PRIMARY KEY CLUSTERED 
(
	[AssignmentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Classes]    Script Date: 10/31/2024 10:32:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Classes](
	[ClassID] [int] IDENTITY(1,1) NOT NULL,
	[ClassName] [varchar](100) NULL,
	[Status] [int] NULL,
	[TeacherID] [int] NULL,
	[CourseID] [int] NULL,
	[SemesterID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[ClassID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Courses]    Script Date: 10/31/2024 10:32:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Courses](
	[CourseID] [int] IDENTITY(1,1) NOT NULL,
	[CourseCode] [nvarchar](100) NULL,
	[CourseName] [varchar](100) NULL,
	[Status] [int] NULL,
 CONSTRAINT [PK__Courses__C92D71873C122860] PRIMARY KEY CLUSTERED 
(
	[CourseID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Lession]    Script Date: 10/31/2024 10:32:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Lession](
	[lession_id] [int] IDENTITY(1,1) NOT NULL,
	[course_id] [int] NULL,
	[title] [nvarchar](255) NOT NULL,
	[video_url] [nvarchar](255) NULL,
	[docs_link] [nvarchar](255) NULL,
	[description] [nvarchar](max) NULL,
	[status] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[lession_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Semesters]    Script Date: 10/31/2024 10:32:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Semesters](
	[SemesterID] [int] IDENTITY(1,1) NOT NULL,
	[SemesterName] [varchar](100) NULL,
	[Year] [int] NULL,
	[Status] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[SemesterID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[studentClass]    Script Date: 10/31/2024 10:32:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[studentClass](
	[studentId] [int] NOT NULL,
	[classId] [int] NOT NULL,
	[joinAt] [datetime] NOT NULL,
	[status] [int] NOT NULL,
 CONSTRAINT [PK__studentC__BA46A57BA2E05C52] PRIMARY KEY CLUSTERED 
(
	[studentId] ASC,
	[classId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[StudentLession]    Script Date: 10/31/2024 10:32:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[StudentLession](
	[student_id] [int] NOT NULL,
	[lession_id] [int] NOT NULL,
	[completion_date] [date] NULL,
	[is_completed] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[student_id] ASC,
	[lession_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Students]    Script Date: 10/31/2024 10:32:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Students](
	[StudentID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NULL,
	[Email] [varchar](100) NULL,
	[Phone] [varchar](20) NULL,
	[Status] [int] NULL,
 CONSTRAINT [PK__Students__32C52A79427D60C0] PRIMARY KEY CLUSTERED 
(
	[StudentID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Submissions]    Script Date: 10/31/2024 10:32:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Submissions](
	[SubmissionID] [int] IDENTITY(1,1) NOT NULL,
	[SubmissionContent] [nvarchar](1000) NULL,
	[StudentID] [int] NULL,
	[AssignmentID] [int] NULL,
	[SubmissionDate] [datetime] NULL,
	[Grade] [float] NULL,
 CONSTRAINT [PK__Submissi__449EE105C8B73DDF] PRIMARY KEY CLUSTERED 
(
	[SubmissionID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Teachers]    Script Date: 10/31/2024 10:32:29 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Teachers](
	[TeacherID] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](100) NULL,
	[Email] [varchar](100) NULL,
	[Phone] [varchar](20) NULL,
	[Status] [int] NULL,
	[Avatar] [nvarchar](500) NULL,
 CONSTRAINT [PK__Teachers__EDF2594451823183] PRIMARY KEY CLUSTERED 
(
	[TeacherID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Admin] ON 

INSERT [dbo].[Admin] ([id], [name], [email], [phone], [password], [status], [avatar]) VALUES (1, N'Lê Tấn Kim', N'Admin@fpt.edu.vn', N'0865341745', N'123456789', 1, NULL)
INSERT [dbo].[Admin] ([id], [name], [email], [phone], [password], [status], [avatar]) VALUES (2, N'Công ty TNHH 3DO', N'4userfpt@gmail.com', N'0865341744', N'123456789', 0, NULL)
SET IDENTITY_INSERT [dbo].[Admin] OFF
GO
SET IDENTITY_INSERT [dbo].[Assignments] ON 

INSERT [dbo].[Assignments] ([AssignmentID], [Title], [Description], [DueDate], [ClassID], [Type], [Status]) VALUES (4, N'Bài tâp về nhà', N'Hãy làm bài viết về chủ đề học bài', CAST(N'2024-06-11T17:50:00.000' AS DateTime), 2, 2, 1)
INSERT [dbo].[Assignments] ([AssignmentID], [Title], [Description], [DueDate], [ClassID], [Type], [Status]) VALUES (6, N'Bài tập về nhà', N'Bài tập hôm nay', CAST(N'2024-06-12T06:22:00.000' AS DateTime), 6, 1, 1)
INSERT [dbo].[Assignments] ([AssignmentID], [Title], [Description], [DueDate], [ClassID], [Type], [Status]) VALUES (7, N'Baid tập mới', N'Abc', CAST(N'2024-06-12T16:10:00.000' AS DateTime), 6, 2, 1)
INSERT [dbo].[Assignments] ([AssignmentID], [Title], [Description], [DueDate], [ClassID], [Type], [Status]) VALUES (8, N'Bài tập mới', N'test', CAST(N'2024-10-21T21:53:00.000' AS DateTime), 5, 1, 1)
SET IDENTITY_INSERT [dbo].[Assignments] OFF
GO
SET IDENTITY_INSERT [dbo].[Classes] ON 

INSERT [dbo].[Classes] ([ClassID], [ClassName], [Status], [TeacherID], [CourseID], [SemesterID]) VALUES (2, N'SE1705', 1, 2, 1, 2)
INSERT [dbo].[Classes] ([ClassID], [ClassName], [Status], [TeacherID], [CourseID], [SemesterID]) VALUES (3, N'SE1706', 1, 2, 1, 2)
INSERT [dbo].[Classes] ([ClassID], [ClassName], [Status], [TeacherID], [CourseID], [SemesterID]) VALUES (4, N' b', 1, 2, 3, 2)
INSERT [dbo].[Classes] ([ClassID], [ClassName], [Status], [TeacherID], [CourseID], [SemesterID]) VALUES (5, N'SE1716', 1, 6, 5, 3)
INSERT [dbo].[Classes] ([ClassID], [ClassName], [Status], [TeacherID], [CourseID], [SemesterID]) VALUES (6, N'SE1721', 1, 7, 5, 2)
SET IDENTITY_INSERT [dbo].[Classes] OFF
GO
SET IDENTITY_INSERT [dbo].[Courses] ON 

INSERT [dbo].[Courses] ([CourseID], [CourseCode], [CourseName], [Status]) VALUES (1, N'ENT001', N'English 1 (Topnotch Fundamental)', 1)
INSERT [dbo].[Courses] ([CourseID], [CourseCode], [CourseName], [Status]) VALUES (3, N'ENT203', N'English 3 (Top Notch 2)', 1)
INSERT [dbo].[Courses] ([CourseID], [CourseCode], [CourseName], [Status]) VALUES (4, N'ENT104', N'English 2 (Top Notch 1)', 1)
INSERT [dbo].[Courses] ([CourseID], [CourseCode], [CourseName], [Status]) VALUES (5, N'SWD392', N'Software Architecture and Design', 1)
SET IDENTITY_INSERT [dbo].[Courses] OFF
GO
SET IDENTITY_INSERT [dbo].[Lession] ON 

INSERT [dbo].[Lession] ([lession_id], [course_id], [title], [video_url], [docs_link], [description], [status]) VALUES (1, 5, N'Demo', N'123', N'no', N'Bài học', N'active')
INSERT [dbo].[Lession] ([lession_id], [course_id], [title], [video_url], [docs_link], [description], [status]) VALUES (2, 5, N'Lession 2', N'uploads/KimLTCE170469_HelloWorld.mp4', N'', N'Bài học 2', N'inactive')
INSERT [dbo].[Lession] ([lession_id], [course_id], [title], [video_url], [docs_link], [description], [status]) VALUES (3, 5, N'Docs', NULL, N'https://docs.google.com/document/d/1x3lQe56j-qRunBq8jrOFq4lyTHs62q8g/edit#heading=h.gjdgxs', N'Bài 3', N'active')
INSERT [dbo].[Lession] ([lession_id], [course_id], [title], [video_url], [docs_link], [description], [status]) VALUES (4, 5, N'Bài học mói', N'uploads/Unity Hub 3.8.0 2024-09-05 19-49-10.mp4', N'uploads/FRD_Group1_HuynhLN_ThuanTNN_AnNT_HuyTG.docx', N'Bài học mói', N'active')
INSERT [dbo].[Lession] ([lession_id], [course_id], [title], [video_url], [docs_link], [description], [status]) VALUES (5, 5, N'BBB', N'https://www.youtube.com/watch?v=MlDWce_ekFo', N'', N'11111', N'active')
SET IDENTITY_INSERT [dbo].[Lession] OFF
GO
SET IDENTITY_INSERT [dbo].[Semesters] ON 

INSERT [dbo].[Semesters] ([SemesterID], [SemesterName], [Year], [Status]) VALUES (2, N'FA23', 2023, 1)
INSERT [dbo].[Semesters] ([SemesterID], [SemesterName], [Year], [Status]) VALUES (3, N'FA24', 2024, 1)
SET IDENTITY_INSERT [dbo].[Semesters] OFF
GO
INSERT [dbo].[studentClass] ([studentId], [classId], [joinAt], [status]) VALUES (1, 2, CAST(N'2024-06-12T10:56:03.107' AS DateTime), 1)
INSERT [dbo].[studentClass] ([studentId], [classId], [joinAt], [status]) VALUES (1, 3, CAST(N'2024-06-12T02:27:26.583' AS DateTime), 0)
INSERT [dbo].[studentClass] ([studentId], [classId], [joinAt], [status]) VALUES (1, 4, CAST(N'2024-06-12T10:56:41.730' AS DateTime), 1)
INSERT [dbo].[studentClass] ([studentId], [classId], [joinAt], [status]) VALUES (1, 5, CAST(N'2024-06-12T10:56:41.733' AS DateTime), 1)
INSERT [dbo].[studentClass] ([studentId], [classId], [joinAt], [status]) VALUES (1, 6, CAST(N'2024-06-12T12:03:54.003' AS DateTime), 1)
INSERT [dbo].[studentClass] ([studentId], [classId], [joinAt], [status]) VALUES (2, 2, CAST(N'2024-06-11T15:29:02.613' AS DateTime), 1)
INSERT [dbo].[studentClass] ([studentId], [classId], [joinAt], [status]) VALUES (2, 3, CAST(N'2024-06-11T12:00:13.640' AS DateTime), 1)
INSERT [dbo].[studentClass] ([studentId], [classId], [joinAt], [status]) VALUES (2, 4, CAST(N'2024-10-24T21:04:42.117' AS DateTime), 0)
INSERT [dbo].[studentClass] ([studentId], [classId], [joinAt], [status]) VALUES (2, 5, CAST(N'2024-06-11T15:21:14.480' AS DateTime), 1)
INSERT [dbo].[studentClass] ([studentId], [classId], [joinAt], [status]) VALUES (2, 6, CAST(N'2024-06-12T13:06:54.373' AS DateTime), 1)
INSERT [dbo].[studentClass] ([studentId], [classId], [joinAt], [status]) VALUES (3, 5, CAST(N'2024-10-20T22:01:49.660' AS DateTime), 1)
GO
INSERT [dbo].[StudentLession] ([student_id], [lession_id], [completion_date], [is_completed]) VALUES (2, 2, CAST(N'2024-10-19' AS Date), 1)
INSERT [dbo].[StudentLession] ([student_id], [lession_id], [completion_date], [is_completed]) VALUES (2, 3, CAST(N'2024-10-19' AS Date), 1)
INSERT [dbo].[StudentLession] ([student_id], [lession_id], [completion_date], [is_completed]) VALUES (2, 4, CAST(N'2024-10-20' AS Date), 1)
INSERT [dbo].[StudentLession] ([student_id], [lession_id], [completion_date], [is_completed]) VALUES (3, 3, CAST(N'2024-10-20' AS Date), 1)
GO
SET IDENTITY_INSERT [dbo].[Students] ON 

INSERT [dbo].[Students] ([StudentID], [Name], [Email], [Phone], [Status]) VALUES (1, N'Lê Tấn Kim', N'letankim2003@gmail.com', N'0865341745', 1)
INSERT [dbo].[Students] ([StudentID], [Name], [Email], [Phone], [Status]) VALUES (2, N'Lê Tấn Kim', N'letankim2810@gmail.com', N'0865341744', 1)
INSERT [dbo].[Students] ([StudentID], [Name], [Email], [Phone], [Status]) VALUES (3, N'Test', N'huynhdangkhoa11233@gmail.com', N'0987654321', 1)
SET IDENTITY_INSERT [dbo].[Students] OFF
GO
SET IDENTITY_INSERT [dbo].[Submissions] ON 

INSERT [dbo].[Submissions] ([SubmissionID], [SubmissionContent], [StudentID], [AssignmentID], [SubmissionDate], [Grade]) VALUES (2, N'/upload/submition/061124045328_EOSClient_OnCampus_25.03.2024.zip', 2, 4, CAST(N'2024-06-11T16:53:28.067' AS DateTime), 9)
INSERT [dbo].[Submissions] ([SubmissionID], [SubmissionContent], [StudentID], [AssignmentID], [SubmissionDate], [Grade]) VALUES (3, N'/upload/submition/061224022153_VNPAY-Springboot-Demo.zip', 1, 6, CAST(N'2024-06-12T02:21:53.880' AS DateTime), 10)
INSERT [dbo].[Submissions] ([SubmissionID], [SubmissionContent], [StudentID], [AssignmentID], [SubmissionDate], [Grade]) VALUES (4, N'/upload/submition/061224010903_EOSClient_OnCampus_25.03.2024.zip', 2, 7, CAST(N'2024-06-12T13:09:03.743' AS DateTime), 10)
INSERT [dbo].[Submissions] ([SubmissionID], [SubmissionContent], [StudentID], [AssignmentID], [SubmissionDate], [Grade]) VALUES (5, N'/upload/submition/102024095524_template (1).zip', 2, 8, CAST(N'2024-10-20T21:55:24.047' AS DateTime), 5)
SET IDENTITY_INSERT [dbo].[Submissions] OFF
GO
SET IDENTITY_INSERT [dbo].[Teachers] ON 

INSERT [dbo].[Teachers] ([TeacherID], [Name], [Email], [Phone], [Status], [Avatar]) VALUES (2, N'Test', N'test@gmail.com', N'0865341745', 1, NULL)
INSERT [dbo].[Teachers] ([TeacherID], [Name], [Email], [Phone], [Status], [Avatar]) VALUES (5, N'Đặng Phước Ân', N'andpce@fpt.edu.vn', N'0865341744', 1, NULL)
INSERT [dbo].[Teachers] ([TeacherID], [Name], [Email], [Phone], [Status], [Avatar]) VALUES (6, N'Cuong', N'kimltce170469@fpt.edu.vn', N'0398096051', 1, N'uploads/a2.jpg')
INSERT [dbo].[Teachers] ([TeacherID], [Name], [Email], [Phone], [Status], [Avatar]) VALUES (7, N'Lê Tấn Kim', N'letankim2003@gmail.com', N'0865341741', 1, NULL)
SET IDENTITY_INSERT [dbo].[Teachers] OFF
GO
ALTER TABLE [dbo].[Admin] ADD  CONSTRAINT [DF_Admin_status]  DEFAULT ((1)) FOR [status]
GO
ALTER TABLE [dbo].[Lession] ADD  DEFAULT ('active') FOR [status]
GO
ALTER TABLE [dbo].[StudentLession] ADD  DEFAULT ((0)) FOR [is_completed]
GO
ALTER TABLE [dbo].[Submissions] ADD  CONSTRAINT [DF_Submissions_Grade]  DEFAULT ((-1)) FOR [Grade]
GO
ALTER TABLE [dbo].[Assignments]  WITH CHECK ADD  CONSTRAINT [FK_Assignments_Classes] FOREIGN KEY([ClassID])
REFERENCES [dbo].[Classes] ([ClassID])
GO
ALTER TABLE [dbo].[Assignments] CHECK CONSTRAINT [FK_Assignments_Classes]
GO
ALTER TABLE [dbo].[Classes]  WITH CHECK ADD FOREIGN KEY([CourseID])
REFERENCES [dbo].[Courses] ([CourseID])
GO
ALTER TABLE [dbo].[Classes]  WITH CHECK ADD FOREIGN KEY([TeacherID])
REFERENCES [dbo].[Teachers] ([TeacherID])
GO
ALTER TABLE [dbo].[Classes]  WITH CHECK ADD  CONSTRAINT [FK_Classes_Semesters] FOREIGN KEY([SemesterID])
REFERENCES [dbo].[Semesters] ([SemesterID])
GO
ALTER TABLE [dbo].[Classes] CHECK CONSTRAINT [FK_Classes_Semesters]
GO
ALTER TABLE [dbo].[Lession]  WITH CHECK ADD FOREIGN KEY([course_id])
REFERENCES [dbo].[Courses] ([CourseID])
GO
ALTER TABLE [dbo].[studentClass]  WITH CHECK ADD  CONSTRAINT [FK_studentClass_Classes] FOREIGN KEY([classId])
REFERENCES [dbo].[Classes] ([ClassID])
GO
ALTER TABLE [dbo].[studentClass] CHECK CONSTRAINT [FK_studentClass_Classes]
GO
ALTER TABLE [dbo].[studentClass]  WITH CHECK ADD  CONSTRAINT [FK_studentClass_Students] FOREIGN KEY([studentId])
REFERENCES [dbo].[Students] ([StudentID])
GO
ALTER TABLE [dbo].[studentClass] CHECK CONSTRAINT [FK_studentClass_Students]
GO
ALTER TABLE [dbo].[StudentLession]  WITH CHECK ADD FOREIGN KEY([lession_id])
REFERENCES [dbo].[Lession] ([lession_id])
GO
ALTER TABLE [dbo].[StudentLession]  WITH CHECK ADD FOREIGN KEY([student_id])
REFERENCES [dbo].[Students] ([StudentID])
GO
ALTER TABLE [dbo].[Submissions]  WITH CHECK ADD  CONSTRAINT [FK__Submissio__Assig__2F10007B] FOREIGN KEY([AssignmentID])
REFERENCES [dbo].[Assignments] ([AssignmentID])
GO
ALTER TABLE [dbo].[Submissions] CHECK CONSTRAINT [FK__Submissio__Assig__2F10007B]
GO
ALTER TABLE [dbo].[Submissions]  WITH CHECK ADD  CONSTRAINT [FK__Submissio__Stude__2E1BDC42] FOREIGN KEY([StudentID])
REFERENCES [dbo].[Students] ([StudentID])
GO
ALTER TABLE [dbo].[Submissions] CHECK CONSTRAINT [FK__Submissio__Stude__2E1BDC42]
GO



