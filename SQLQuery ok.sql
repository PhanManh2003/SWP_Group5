create database OCMS
use master
USE [OCMS]
GO
drop database OCMS
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
	[gender] [bit] NULL,
	[AccountStatus] [nvarchar](20) NULL,
	[LastLogin] [datetime] NULL,
	[Avatar] [nvarchar](max) NULL,
 CONSTRAINT [PK__Account__349DA5863FDA2407] PRIMARY KEY CLUSTERED 
(
	[AccountID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO

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

SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Role](
	[role_id] [int] NOT NULL,
	[role_name] [varchar](50) NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

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

INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [gender], [AccountStatus], [LastLogin], [Avatar]) VALUES (1, N'john.doe@email.com', NULL, N'Student', N'Inactive', 1, NULL, CAST(N'2024-03-15T10:30:00.000' AS DateTime), NULL)
INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [gender], [AccountStatus], [LastLogin], [Avatar]) VALUES (2, N'jane.smith@email.com', NULL, N'Teacher', N'Inactive', 0, NULL, CAST(N'2024-03-16T09:15:00.000' AS DateTime), NULL)
INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [gender], [AccountStatus], [LastLogin], [Avatar]) VALUES (3, N'bob.admin@email.com', N'hashed_password_3', N'Staff', N'Inactive', NULL, N'Verified', CAST(N'2024-03-17T08:45:00.000' AS DateTime), NULL)
INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [gender], [AccountStatus], [LastLogin], [Avatar]) VALUES (4, N'alice.student@email.com', N'hashed_password_4', N'Student', N'Active', NULL, N'Pending', CAST(N'2024-03-18T14:20:00.000' AS DateTime), NULL)
INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [gender], [AccountStatus], [LastLogin], [Avatar]) VALUES (5, N'bvcbvc@gmail.com', N'123', NULL, N'Active', NULL, NULL, CAST(N'2024-09-22T17:21:58.357' AS DateTime), NULL)
INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [gender], [AccountStatus], [LastLogin], [Avatar]) VALUES (6, N'vxcv@gmail.com', N'202cb962ac59075b964b07152d234b70', N'Student', N'Active', NULL, NULL, CAST(N'2024-09-22T19:47:33.433' AS DateTime), NULL)
INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [gender], [AccountStatus], [LastLogin], [Avatar]) VALUES (7, N'vinhpham2761123213@gmail.com', N'202cb962ac59075b964b07152d234b70', N'Student', N'Active', 1, NULL, CAST(N'2024-09-22T19:47:54.007' AS DateTime), NULL)
INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [gender], [AccountStatus], [LastLogin], [Avatar]) VALUES (8, N'tej0tojem0m3@10mail.xyz', N'202cb962ac59075b964b07152d234b70', N'Student', N'Active', NULL, NULL, CAST(N'2024-09-22T19:49:48.970' AS DateTime), NULL)
INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [gender], [AccountStatus], [LastLogin], [Avatar]) VALUES (9, N'agfffwwsm@yomail.info', N'827ccb0eea8a706c4c34a16891f84e7b', N'Admin', N'Active', NULL, NULL, CAST(N'2024-09-22T20:27:53.503' AS DateTime), NULL)
INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [gender], [AccountStatus], [LastLogin], [Avatar]) VALUES (10, N'xxn7bjpg@minimail.gq', N'81dc9bdb52d04dc20036dbd8313ed055', N'Student', N'Active', NULL, NULL, CAST(N'2024-09-22T22:36:14.270' AS DateTime), NULL)
INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [gender], [AccountStatus], [LastLogin], [Avatar]) VALUES (11, N'huypham2761@gmail.com', N'', N'Student', N'Active', 1, NULL, CAST(N'2024-09-26T17:01:16.100' AS DateTime), NULL)
INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [gender], [AccountStatus], [LastLogin], [Avatar]) VALUES (12, N'huydepzai@gmail.com', N'', N'Student', N'Active', 0, NULL, CAST(N'2024-09-26T21:57:19.247' AS DateTime), NULL)
INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [gender], [AccountStatus], [LastLogin], [Avatar]) VALUES (13, N'', N'd41d8cd98f00b204e9800998ecf8427e', N'Student', N'Inactive', 1, NULL, CAST(N'2024-09-26T22:11:45.870' AS DateTime), NULL)
INSERT [dbo].[Account] ([AccountID], [Email], [Password], [Role], [Status], [gender], [AccountStatus], [LastLogin], [Avatar]) VALUES (14, N'xmqsw235@flymail.tk', N'81dc9bdb52d04dc20036dbd8313ed055', N'Student', N'Active', 1, NULL, CAST(N'2024-09-26T22:12:36.740' AS DateTime), NULL)
SET IDENTITY_INSERT [dbo].[Account] OFF
GO
INSERT [dbo].[Customer] ([CustomerID], [FullName], [BirthDate], [Gender], [Avatar], [AccountID]) VALUES (1, N'John Doe', CAST(N'1995-05-15' AS Date), N'Male', NULL, 1)
INSERT [dbo].[Customer] ([CustomerID], [FullName], [BirthDate], [Gender], [Avatar], [AccountID]) VALUES (2, N'Alice Student', CAST(N'1998-09-22' AS Date), N'Female', NULL, 4)
GO
INSERT [dbo].[Instructor] ([InstructorID], [Email], [FullName], [Specialization], [Phone], [AccountID]) VALUES (1, N'jane.smith@email.com', N'Jane Smith', N'Computer Science', N'123-456-7890', 2)
GO
INSERT [dbo].[Staff] ([StaffID], [FullName], [Birthday], [Gender], [InjobDate], [AccountID]) VALUES (1, N'Bob Admin', CAST(N'1985-03-10' AS Date), N'Male', CAST(N'2020-01-15' AS Date), 3)
GO
SET ANSI_PADDING ON
GO

ALTER TABLE [dbo].[Account] ADD  CONSTRAINT [UQ__Account__A9D1053423346421] UNIQUE NONCLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO

ALTER TABLE [dbo].[Role] ADD UNIQUE NONCLUSTERED 
(
	[role_name] ASC
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


select * from dbo.Account
delete from dbo.Account where Email = 'manhamsterdam2003@gmail.com';