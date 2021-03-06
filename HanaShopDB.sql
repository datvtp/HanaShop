USE [master]
GO
/****** Object:  Database [HanaShop]    Script Date: 4/11/2020 5:59:31 PM ******/
CREATE DATABASE [HanaShop]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'HanaShop', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\HanaShop.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'HanaShop_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL14.SQLEXPRESS\MSSQL\DATA\HanaShop_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
GO
ALTER DATABASE [HanaShop] SET COMPATIBILITY_LEVEL = 140
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [HanaShop].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [HanaShop] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [HanaShop] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [HanaShop] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [HanaShop] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [HanaShop] SET ARITHABORT OFF 
GO
ALTER DATABASE [HanaShop] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [HanaShop] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [HanaShop] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [HanaShop] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [HanaShop] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [HanaShop] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [HanaShop] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [HanaShop] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [HanaShop] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [HanaShop] SET  DISABLE_BROKER 
GO
ALTER DATABASE [HanaShop] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [HanaShop] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [HanaShop] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [HanaShop] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [HanaShop] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [HanaShop] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [HanaShop] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [HanaShop] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [HanaShop] SET  MULTI_USER 
GO
ALTER DATABASE [HanaShop] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [HanaShop] SET DB_CHAINING OFF 
GO
ALTER DATABASE [HanaShop] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [HanaShop] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [HanaShop] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [HanaShop] SET QUERY_STORE = OFF
GO
USE [HanaShop]
GO
/****** Object:  Table [dbo].[tbl_Category]    Script Date: 4/11/2020 5:59:31 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Category](
	[CategoryId] [int] NOT NULL,
	[CategoryName] [nvarchar](50) NULL,
 CONSTRAINT [PK_tbl_Category] PRIMARY KEY CLUSTERED 
(
	[CategoryId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Food]    Script Date: 4/11/2020 5:59:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Food](
	[FoodId] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NULL,
	[Image] [nvarchar](50) NULL,
	[Description] [nvarchar](200) NULL,
	[Price] [int] NULL,
	[Quantity] [int] NULL,
	[CreateTime] [datetime] NULL,
	[CategoryId] [int] NULL,
	[StatusId] [int] NULL,
	[UpdateUser] [nvarchar](50) NULL,
	[UpdateTime] [datetime] NULL,
 CONSTRAINT [PK_tbl_Food] PRIMARY KEY CLUSTERED 
(
	[FoodId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Invoice]    Script Date: 4/11/2020 5:59:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Invoice](
	[InvoiceId] [int] IDENTITY(1,1) NOT NULL,
	[CreateTime] [datetime] NULL,
	[TotalPrice] [int] NULL,
	[Email] [nvarchar](50) NULL,
 CONSTRAINT [PK_tbl_Invoice] PRIMARY KEY CLUSTERED 
(
	[InvoiceId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_InvoiceDetail]    Script Date: 4/11/2020 5:59:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_InvoiceDetail](
	[InvoiceDetailId] [int] IDENTITY(1,1) NOT NULL,
	[FoodId] [int] NULL,
	[Quantity] [int] NULL,
	[TotalPrice] [int] NULL,
	[InvoiceId] [int] NULL,
 CONSTRAINT [PK_tbl_InvoiceDetail] PRIMARY KEY CLUSTERED 
(
	[InvoiceDetailId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Role]    Script Date: 4/11/2020 5:59:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Role](
	[RoleId] [int] NOT NULL,
	[RoleName] [nvarchar](50) NULL,
 CONSTRAINT [PK_tbl_Role] PRIMARY KEY CLUSTERED 
(
	[RoleId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_Status]    Script Date: 4/11/2020 5:59:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_Status](
	[StatusId] [int] NOT NULL,
	[StatusName] [nvarchar](50) NULL,
 CONSTRAINT [PK_tbl_Status] PRIMARY KEY CLUSTERED 
(
	[StatusId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[tbl_User]    Script Date: 4/11/2020 5:59:32 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[tbl_User](
	[Email] [nvarchar](50) NOT NULL,
	[Name] [nvarchar](50) NULL,
	[Password] [nvarchar](50) NULL,
	[RoleId] [int] NULL,
 CONSTRAINT [PK_tbl_User] PRIMARY KEY CLUSTERED 
(
	[Email] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[tbl_Category] ([CategoryId], [CategoryName]) VALUES (1, N'Food')
INSERT [dbo].[tbl_Category] ([CategoryId], [CategoryName]) VALUES (2, N'Drink')
INSERT [dbo].[tbl_Category] ([CategoryId], [CategoryName]) VALUES (3, N'Cake')
INSERT [dbo].[tbl_Category] ([CategoryId], [CategoryName]) VALUES (4, N'Candie')
SET IDENTITY_INSERT [dbo].[tbl_Food] ON 

INSERT [dbo].[tbl_Food] ([FoodId], [Name], [Image], [Description], [Price], [Quantity], [CreateTime], [CategoryId], [StatusId], [UpdateUser], [UpdateTime]) VALUES (1, N'Pepsi', N'pepsi.jpg', N'for 1 people', 2, 50, CAST(N'2020-02-28T11:28:18.000' AS DateTime), 2, 1, N'admin@gmail.com', CAST(N'2020-03-02T18:32:37.000' AS DateTime))
INSERT [dbo].[tbl_Food] ([FoodId], [Name], [Image], [Description], [Price], [Quantity], [CreateTime], [CategoryId], [StatusId], [UpdateUser], [UpdateTime]) VALUES (2, N'Banh Mi', N'banhmi.jpg', N'for 1 peofor', 10, 50, CAST(N'2020-02-28T11:30:17.000' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[tbl_Food] ([FoodId], [Name], [Image], [Description], [Price], [Quantity], [CreateTime], [CategoryId], [StatusId], [UpdateUser], [UpdateTime]) VALUES (3, N'Pho', N'pho.jpg', N'for 1 people', 20, 50, CAST(N'2020-02-28T11:30:41.000' AS DateTime), 1, 1, N'admin@gmail.com', CAST(N'2020-02-29T18:08:09.000' AS DateTime))
INSERT [dbo].[tbl_Food] ([FoodId], [Name], [Image], [Description], [Price], [Quantity], [CreateTime], [CategoryId], [StatusId], [UpdateUser], [UpdateTime]) VALUES (4, N'Pizza', N'pizza.jpg', N'for 5 people', 100, 45, CAST(N'2020-02-28T11:31:13.000' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[tbl_Food] ([FoodId], [Name], [Image], [Description], [Price], [Quantity], [CreateTime], [CategoryId], [StatusId], [UpdateUser], [UpdateTime]) VALUES (5, N'Hamburger', N'hamburger.jpg', N'for 1 people', 20, 49, CAST(N'2020-02-28T11:31:34.000' AS DateTime), 1, 1, N'admin@gmail.com', CAST(N'2020-02-29T18:06:10.000' AS DateTime))
INSERT [dbo].[tbl_Food] ([FoodId], [Name], [Image], [Description], [Price], [Quantity], [CreateTime], [CategoryId], [StatusId], [UpdateUser], [UpdateTime]) VALUES (6, N'SandWich', N'sandwich.jpg', N'for 1 people', 20, 48, CAST(N'2020-02-28T11:33:07.000' AS DateTime), 1, 1, N'admin@gmail.com', CAST(N'2020-02-28T11:33:43.000' AS DateTime))
INSERT [dbo].[tbl_Food] ([FoodId], [Name], [Image], [Description], [Price], [Quantity], [CreateTime], [CategoryId], [StatusId], [UpdateUser], [UpdateTime]) VALUES (7, N'Keo Play', N'keoplay.jpg', N'for 1 people', 5, 50, CAST(N'2020-03-01T15:53:49.000' AS DateTime), 4, 1, NULL, NULL)
INSERT [dbo].[tbl_Food] ([FoodId], [Name], [Image], [Description], [Price], [Quantity], [CreateTime], [CategoryId], [StatusId], [UpdateUser], [UpdateTime]) VALUES (8, N'Donut Kem', N'Donutkem.jpg', N'for 1 people', 8, 50, CAST(N'2020-03-01T15:54:28.000' AS DateTime), 3, 1, NULL, NULL)
INSERT [dbo].[tbl_Food] ([FoodId], [Name], [Image], [Description], [Price], [Quantity], [CreateTime], [CategoryId], [StatusId], [UpdateUser], [UpdateTime]) VALUES (9, N'Fanta Cam', N'fanta.jpg', N'for 1 people', 3, 50, CAST(N'2020-03-01T15:54:59.000' AS DateTime), 2, 1, NULL, NULL)
INSERT [dbo].[tbl_Food] ([FoodId], [Name], [Image], [Description], [Price], [Quantity], [CreateTime], [CategoryId], [StatusId], [UpdateUser], [UpdateTime]) VALUES (10, N'Red Bull', N'redbull.jpg', N'for 1 people', 3, 50, CAST(N'2020-03-01T15:55:29.000' AS DateTime), 2, 1, NULL, NULL)
INSERT [dbo].[tbl_Food] ([FoodId], [Name], [Image], [Description], [Price], [Quantity], [CreateTime], [CategoryId], [StatusId], [UpdateUser], [UpdateTime]) VALUES (11, N'7 Up', N'7up.jpg', N'for 1 people', 3, 50, CAST(N'2020-03-01T15:55:58.000' AS DateTime), 2, 1, NULL, NULL)
INSERT [dbo].[tbl_Food] ([FoodId], [Name], [Image], [Description], [Price], [Quantity], [CreateTime], [CategoryId], [StatusId], [UpdateUser], [UpdateTime]) VALUES (12, N'Sting', N'sting.jpg', N'for 1 people', 3, 50, CAST(N'2020-03-01T15:56:17.000' AS DateTime), 2, 1, NULL, NULL)
INSERT [dbo].[tbl_Food] ([FoodId], [Name], [Image], [Description], [Price], [Quantity], [CreateTime], [CategoryId], [StatusId], [UpdateUser], [UpdateTime]) VALUES (13, N'Com Chien', N'comchien.jpg', N'for 1 peoplf', 15, 50, CAST(N'2020-03-01T15:58:03.000' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[tbl_Food] ([FoodId], [Name], [Image], [Description], [Price], [Quantity], [CreateTime], [CategoryId], [StatusId], [UpdateUser], [UpdateTime]) VALUES (14, N'Cari Ga', N'cariga.jpg', N'for 1 people', 20, 50, CAST(N'2020-03-01T15:58:42.000' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[tbl_Food] ([FoodId], [Name], [Image], [Description], [Price], [Quantity], [CreateTime], [CategoryId], [StatusId], [UpdateUser], [UpdateTime]) VALUES (15, N'Khoai Tay Chien', N'khoaitaychien.jpg', N'for 1 people', 8, 50, CAST(N'2020-03-01T15:59:02.000' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[tbl_Food] ([FoodId], [Name], [Image], [Description], [Price], [Quantity], [CreateTime], [CategoryId], [StatusId], [UpdateUser], [UpdateTime]) VALUES (16, N'Muc Chien Gion', N'mucchiengion.jpg', N'for 1 people', 15, 50, CAST(N'2020-03-01T15:59:40.000' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[tbl_Food] ([FoodId], [Name], [Image], [Description], [Price], [Quantity], [CreateTime], [CategoryId], [StatusId], [UpdateUser], [UpdateTime]) VALUES (17, N'Ga Ran', N'garan.jpg', N'for 1 people', 15, 50, CAST(N'2020-03-01T16:00:03.000' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[tbl_Food] ([FoodId], [Name], [Image], [Description], [Price], [Quantity], [CreateTime], [CategoryId], [StatusId], [UpdateUser], [UpdateTime]) VALUES (18, N'Xoi Man', N'xoiman.jpg', N'for 1 people', 10, 50, CAST(N'2020-03-01T16:00:28.000' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[tbl_Food] ([FoodId], [Name], [Image], [Description], [Price], [Quantity], [CreateTime], [CategoryId], [StatusId], [UpdateUser], [UpdateTime]) VALUES (19, N'Banh Khot', N'banhkhot.jpg', N'for 1 people', 10, 46, CAST(N'2020-03-01T16:00:59.000' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[tbl_Food] ([FoodId], [Name], [Image], [Description], [Price], [Quantity], [CreateTime], [CategoryId], [StatusId], [UpdateUser], [UpdateTime]) VALUES (20, N'Banh Bao', N'banhbao.jpg', N'for 1 people', 10, 49, CAST(N'2020-03-01T16:01:24.000' AS DateTime), 1, 1, NULL, NULL)
INSERT [dbo].[tbl_Food] ([FoodId], [Name], [Image], [Description], [Price], [Quantity], [CreateTime], [CategoryId], [StatusId], [UpdateUser], [UpdateTime]) VALUES (21, N'Coca Cola', N'cocacola.jpg', N'for 1 people', 3, 10, CAST(N'2020-03-01T16:53:19.000' AS DateTime), 2, 1, N'admin@gmail.com', CAST(N'2020-03-02T17:45:41.000' AS DateTime))
INSERT [dbo].[tbl_Food] ([FoodId], [Name], [Image], [Description], [Price], [Quantity], [CreateTime], [CategoryId], [StatusId], [UpdateUser], [UpdateTime]) VALUES (22, N'Banh Xeo', N'banhxeo.jpg', N'for 1 people', 15, 50, CAST(N'2020-03-01T16:54:20.000' AS DateTime), 1, 1, N'admin@gmail.com', CAST(N'2020-03-06T16:15:48.000' AS DateTime))
INSERT [dbo].[tbl_Food] ([FoodId], [Name], [Image], [Description], [Price], [Quantity], [CreateTime], [CategoryId], [StatusId], [UpdateUser], [UpdateTime]) VALUES (23, N'Pho Mai Que', N'phomaique.jpg', N'for 1 people', 15, 50, CAST(N'2020-03-06T16:16:42.000' AS DateTime), 1, 1, NULL, NULL)
SET IDENTITY_INSERT [dbo].[tbl_Food] OFF
SET IDENTITY_INSERT [dbo].[tbl_Invoice] ON 

INSERT [dbo].[tbl_Invoice] ([InvoiceId], [CreateTime], [TotalPrice], [Email]) VALUES (1, CAST(N'2020-02-28T18:49:28.000' AS DateTime), 122, N'user@gmail.com')
INSERT [dbo].[tbl_Invoice] ([InvoiceId], [CreateTime], [TotalPrice], [Email]) VALUES (2, CAST(N'2020-02-28T19:50:04.000' AS DateTime), 210, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Invoice] ([InvoiceId], [CreateTime], [TotalPrice], [Email]) VALUES (3, CAST(N'2020-02-28T21:30:21.000' AS DateTime), 142, N'user@gmail.com')
INSERT [dbo].[tbl_Invoice] ([InvoiceId], [CreateTime], [TotalPrice], [Email]) VALUES (4, CAST(N'2020-02-29T18:06:37.000' AS DateTime), 20, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Invoice] ([InvoiceId], [CreateTime], [TotalPrice], [Email]) VALUES (5, CAST(N'2020-02-29T18:08:34.000' AS DateTime), 20, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Invoice] ([InvoiceId], [CreateTime], [TotalPrice], [Email]) VALUES (6, CAST(N'2020-02-29T18:09:14.000' AS DateTime), 960, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Invoice] ([InvoiceId], [CreateTime], [TotalPrice], [Email]) VALUES (8, CAST(N'2020-03-01T21:06:10.000' AS DateTime), 102, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Invoice] ([InvoiceId], [CreateTime], [TotalPrice], [Email]) VALUES (9, CAST(N'2020-03-01T21:32:40.000' AS DateTime), 12, N'vanthanhphuongdat1309@gmail.com')
INSERT [dbo].[tbl_Invoice] ([InvoiceId], [CreateTime], [TotalPrice], [Email]) VALUES (10, CAST(N'2020-03-01T21:33:41.000' AS DateTime), 12, N'user@gmail.com')
INSERT [dbo].[tbl_Invoice] ([InvoiceId], [CreateTime], [TotalPrice], [Email]) VALUES (11, CAST(N'2020-03-01T21:33:57.000' AS DateTime), 102, N'user@gmail.com')
INSERT [dbo].[tbl_Invoice] ([InvoiceId], [CreateTime], [TotalPrice], [Email]) VALUES (12, CAST(N'2020-03-01T21:34:14.000' AS DateTime), 12, N'user@gmail.com')
INSERT [dbo].[tbl_Invoice] ([InvoiceId], [CreateTime], [TotalPrice], [Email]) VALUES (13, CAST(N'2020-03-02T17:53:55.000' AS DateTime), 112, N'user@gmail.com')
INSERT [dbo].[tbl_Invoice] ([InvoiceId], [CreateTime], [TotalPrice], [Email]) VALUES (15, CAST(N'2020-03-02T21:12:23.000' AS DateTime), 15, N'user@gmail.com')
INSERT [dbo].[tbl_Invoice] ([InvoiceId], [CreateTime], [TotalPrice], [Email]) VALUES (16, CAST(N'2020-03-06T16:14:15.000' AS DateTime), 900, N'user@gmail.com')
SET IDENTITY_INSERT [dbo].[tbl_Invoice] OFF
SET IDENTITY_INSERT [dbo].[tbl_InvoiceDetail] ON 

INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (1, 1, 1, 2, 1)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (2, 4, 1, 100, 1)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (3, 5, 1, 20, 1)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (4, 2, 1, 10, 2)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (5, 4, 2, 200, 2)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (6, 1, 1, 2, 3)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (7, 4, 1, 100, 3)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (8, 6, 2, 40, 3)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (9, 5, 1, 20, 4)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (10, 5, 1, 20, 5)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (11, 5, 48, 960, 6)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (13, 1, 1, 2, 8)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (14, 4, 1, 100, 8)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (15, 1, 1, 2, 9)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (16, 19, 1, 10, 9)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (17, 1, 1, 2, 10)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (18, 19, 1, 10, 10)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (19, 1, 1, 2, 11)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (20, 4, 1, 100, 11)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (21, 1, 1, 2, 12)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (22, 20, 1, 10, 12)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (23, 1, 1, 2, 13)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (24, 19, 1, 10, 13)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (25, 4, 1, 100, 13)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (30, 22, 1, 15, 15)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (31, 21, 50, 150, 16)
INSERT [dbo].[tbl_InvoiceDetail] ([InvoiceDetailId], [FoodId], [Quantity], [TotalPrice], [InvoiceId]) VALUES (32, 22, 50, 750, 16)
SET IDENTITY_INSERT [dbo].[tbl_InvoiceDetail] OFF
INSERT [dbo].[tbl_Role] ([RoleId], [RoleName]) VALUES (1, N'admin')
INSERT [dbo].[tbl_Role] ([RoleId], [RoleName]) VALUES (2, N'user')
INSERT [dbo].[tbl_Status] ([StatusId], [StatusName]) VALUES (1, N'active')
INSERT [dbo].[tbl_Status] ([StatusId], [StatusName]) VALUES (2, N'inactive')
INSERT [dbo].[tbl_User] ([Email], [Name], [Password], [RoleId]) VALUES (N'admin@gmail.com', N'ADMIN', N'12345', 1)
INSERT [dbo].[tbl_User] ([Email], [Name], [Password], [RoleId]) VALUES (N'user@gmail.com', N'USER', N'12345', 2)
INSERT [dbo].[tbl_User] ([Email], [Name], [Password], [RoleId]) VALUES (N'vanthanhphuongdat1309@gmail.com', N'Đat Văn', N'', 2)
ALTER TABLE [dbo].[tbl_Food]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Food_tbl_Category] FOREIGN KEY([CategoryId])
REFERENCES [dbo].[tbl_Category] ([CategoryId])
GO
ALTER TABLE [dbo].[tbl_Food] CHECK CONSTRAINT [FK_tbl_Food_tbl_Category]
GO
ALTER TABLE [dbo].[tbl_Food]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Food_tbl_Status] FOREIGN KEY([StatusId])
REFERENCES [dbo].[tbl_Status] ([StatusId])
GO
ALTER TABLE [dbo].[tbl_Food] CHECK CONSTRAINT [FK_tbl_Food_tbl_Status]
GO
ALTER TABLE [dbo].[tbl_Food]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Food_tbl_User] FOREIGN KEY([UpdateUser])
REFERENCES [dbo].[tbl_User] ([Email])
GO
ALTER TABLE [dbo].[tbl_Food] CHECK CONSTRAINT [FK_tbl_Food_tbl_User]
GO
ALTER TABLE [dbo].[tbl_Invoice]  WITH CHECK ADD  CONSTRAINT [FK_tbl_Invoice_tbl_User] FOREIGN KEY([Email])
REFERENCES [dbo].[tbl_User] ([Email])
GO
ALTER TABLE [dbo].[tbl_Invoice] CHECK CONSTRAINT [FK_tbl_Invoice_tbl_User]
GO
ALTER TABLE [dbo].[tbl_InvoiceDetail]  WITH CHECK ADD  CONSTRAINT [FK_tbl_InvoiceDetail_tbl_Food] FOREIGN KEY([FoodId])
REFERENCES [dbo].[tbl_Food] ([FoodId])
GO
ALTER TABLE [dbo].[tbl_InvoiceDetail] CHECK CONSTRAINT [FK_tbl_InvoiceDetail_tbl_Food]
GO
ALTER TABLE [dbo].[tbl_InvoiceDetail]  WITH CHECK ADD  CONSTRAINT [FK_tbl_InvoiceDetail_tbl_Invoice] FOREIGN KEY([InvoiceId])
REFERENCES [dbo].[tbl_Invoice] ([InvoiceId])
GO
ALTER TABLE [dbo].[tbl_InvoiceDetail] CHECK CONSTRAINT [FK_tbl_InvoiceDetail_tbl_Invoice]
GO
ALTER TABLE [dbo].[tbl_User]  WITH CHECK ADD  CONSTRAINT [FK_tbl_User_tbl_Role] FOREIGN KEY([RoleId])
REFERENCES [dbo].[tbl_Role] ([RoleId])
GO
ALTER TABLE [dbo].[tbl_User] CHECK CONSTRAINT [FK_tbl_User_tbl_Role]
GO
USE [master]
GO
ALTER DATABASE [HanaShop] SET  READ_WRITE 
GO
