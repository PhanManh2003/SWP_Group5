CREATE DATABASE OCMS
go

IF EXISTS (SELECT * FROM sys.databases WHERE name = 'OCMS')
	DROP DATABASE OCMS
GO
USE [OCMS]
GO
/****** Object:  Table [dbo].[Account]    Script Date: 9/22/2024 10:45:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Account](
	[AccountID] [int] IDENTITY(1,1) NOT NULL,
	[Email] [nvarchar](100) NULL,
	[Password] [nvarchar](255) NULL,
	[Role] [nvarchar](50) NULL,
	[Status] [nvarchar](20) NULL,
	[AccountStatus] [nvarchar](20) NULL,
	[LastLogin] [datetime] NULL,
 CONSTRAINT [PK__Account__349DA5863FDA2407] PRIMARY KEY CLUSTERED 
(
	[AccountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 9/22/2024 10:45:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[CustomerID] [int] NOT NULL,
	[FullName] [nvarchar](100) NULL,
	[BirthDate] [date] NULL,
	[Gender] [nvarchar](10) NULL,
	[Avatar] [varbinary](max) NULL,
	[AccountID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[CustomerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Instructor]    Script Date: 9/22/2024 10:45:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Instructor](
	[InstructorID] [int] NOT NULL,
	[Email] [nvarchar](100) NULL,
	[FullName] [nvarchar](100) NULL,
	[Specialization] [nvarchar](100) NULL,
	[Phone] [nvarchar](20) NULL,
	[AccountID] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[InstructorID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Staff]    Script Date: 9/22/2024 10:45:25 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Staff](
	[StaffID] [int] NOT NULL,
	[FullName] [nvarchar](100) NULL,
	[Birthday] [date] NULL,
	[Gender] [nvarchar](10) NULL,
	[InjobDate] [date] NULL,
	[AccountID] [int] NOT NULL,
 CONSTRAINT [PK_Staff] PRIMARY KEY CLUSTERED 
(
	[StaffID] ASC,
	[AccountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Account] ON 
GO
INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [AccountStatus], [LastLogin]) VALUES (1, N'john.doe@email.com', N'hashed_password_1', N'Student', N'Active', N'Verified', CAST(N'2024-03-15T10:30:00.000' AS DateTime))
GO
INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [AccountStatus], [LastLogin]) VALUES (2, N'jane.smith@email.com', N'hashed_password_2', N'Instructor', N'Active', N'Verified', CAST(N'2024-03-16T09:15:00.000' AS DateTime))
GO
INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [AccountStatus], [LastLogin]) VALUES (3, N'bob.admin@email.com', N'hashed_password_3', N'Staff', N'Active', N'Verified', CAST(N'2024-03-17T08:45:00.000' AS DateTime))
GO
INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [AccountStatus], [LastLogin]) VALUES (4, N'alice.student@email.com', N'hashed_password_4', N'Student', N'Active', N'Pending', CAST(N'2024-03-18T14:20:00.000' AS DateTime))
GO
INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [AccountStatus], [LastLogin]) VALUES (5, N'bvcbvc@gmail.com', N'123', NULL, NULL, NULL, CAST(N'2024-09-22T17:21:58.357' AS DateTime))
GO
INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [AccountStatus], [LastLogin]) VALUES (6, N'vxcv@gmail.com', N'202cb962ac59075b964b07152d234b70', N'Student', NULL, NULL, CAST(N'2024-09-22T19:47:33.433' AS DateTime))
GO
INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [AccountStatus], [LastLogin]) VALUES (7, N'vinhpham2761@gmail.com', N'202cb962ac59075b964b07152d234b70', N'Student', NULL, NULL, CAST(N'2024-09-22T19:47:54.007' AS DateTime))
GO
INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [AccountStatus], [LastLogin]) VALUES (8, N'tej0tojem0m3@10mail.xyz', N'202cb962ac59075b964b07152d234b70', N'Student', N'Active', NULL, CAST(N'2024-09-22T19:49:48.970' AS DateTime))
GO
INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [AccountStatus], [LastLogin]) VALUES (9, N'agfffwwsm@yomail.info', N'81dc9bdb52d04dc20036dbd8313ed055', N'Student', N'Active', NULL, CAST(N'2024-09-22T20:27:53.503' AS DateTime))
GO
INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [AccountStatus], [LastLogin]) VALUES (10, N'xxn7bjpg@minimail.gq', N'81dc9bdb52d04dc20036dbd8313ed055', N'Student', N'Active', NULL, CAST(N'2024-09-22T22:36:14.270' AS DateTime))
GO
SET IDENTITY_INSERT [dbo].[Account] OFF
GO
INSERT [dbo].[Customer] ([CustomerID], [FullName], [BirthDate], [Gender], [Avatar], [AccountID]) VALUES (1, N'John Doe', CAST(N'1995-05-15' AS Date), N'Male', NULL, 1)
GO
INSERT [dbo].[Customer] ([CustomerID], [FullName], [BirthDate], [Gender], [Avatar], [AccountID]) VALUES (2, N'Alice Student', CAST(N'1998-09-22' AS Date), N'Female', NULL, 4)
GO
INSERT [dbo].[Instructor] ([InstructorID], [Email], [FullName], [Specialization], [Phone], [AccountID]) VALUES (1, N'jane.smith@email.com', N'Jane Smith', N'Computer Science', N'123-456-7890', 2)
GO
INSERT [dbo].[Staff] ([StaffID], [FullName], [Birthday], [Gender], [InjobDate], [AccountID]) VALUES (1, N'Bob Admin', CAST(N'1985-03-10' AS Date), N'Male', CAST(N'2020-01-15' AS Date), 3)
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Account__A9D1053423346421]    Script Date: 9/22/2024 10:45:25 PM ******/
ALTER TABLE [dbo].[Account] ADD  CONSTRAINT [UQ__Account__A9D1053423346421] UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Customer]  WITH CHECK ADD  CONSTRAINT [FK__Customer__Accoun__276EDEB3] FOREIGN KEY([AccountID])
REFERENCES [dbo].[Account] ([AccountID])
GO
ALTER TABLE [dbo].[Customer] CHECK CONSTRAINT [FK__Customer__Accoun__276EDEB3]
GO
ALTER TABLE [dbo].[Instructor]  WITH CHECK ADD  CONSTRAINT [FK__Instructo__Accou__2A4B4B5E] FOREIGN KEY([AccountID])
REFERENCES [dbo].[Account] ([AccountID])
GO
ALTER TABLE [dbo].[Instructor] CHECK CONSTRAINT [FK__Instructo__Accou__2A4B4B5E]
GO
ALTER TABLE [dbo].[Staff]  WITH CHECK ADD  CONSTRAINT [FK__Staff__AccountID__2D27B809] FOREIGN KEY([AccountID])
REFERENCES [dbo].[Account] ([AccountID])
GO
ALTER TABLE [dbo].[Staff] CHECK CONSTRAINT [FK__Staff__AccountID__2D27B809]
GO
