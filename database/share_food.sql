-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 03, 2019 at 05:48 PM
-- Server version: 10.1.38-MariaDB
-- PHP Version: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `share_food`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `image` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`, `image`) VALUES
(1, 'Kem và thức uống', 'http://www.monngon.tv/uploads/images/2017/04/15/0c317271371a02a2bd6b439e22184af9-cach-lam-kem-ky-slide.jpg'),
(2, 'Món bánh', 'https://kokotaru.com/wp-content/uploads/2009/09/F-cach-lam-banh-cupcake-bo-butterfly-cakes.jpg?9d7bd4&9d7bd4'),
(3, 'Món bún', 'http://static.wifi.danang.gov.vn/upload/userfiles/images/2-bun-thit-nuong-bun-mam-nem-heo-quay-2-ly-nuoc-Pepsi-tai-Quan-Ngon-Nhu-Bun.jpg'),
(4, 'Món canh', 'https://kokotaru.com/wp-content/uploads/2017/04/F-Kokotaru-cach-nau-canh-Atiso-chan-gio.jpg'),
(5, 'Món cháo', 'https://daotaobeptruong.vn/images/daotaobeptruong/chao_ca_loc_cho_be.jpg'),
(6, 'Món chay', 'http://bep360.net/wp-content/uploads/2016/07/cach-lam-goi-cuon-chay.jpg'),
(7, 'Món chè', 'https://i.ytimg.com/vi/BtKH3Oszboo/maxresdefault.jpg'),
(8, 'Món chiên', 'http://mayfoods.vn/wp-content/uploads/2017/11/20160322_ANKT_CaHoiChienXu-6-c0d55.jpg'),
(9, 'Món cơm', 'https://znews-photo.zadn.vn/w860/Uploaded/mdf_eioxrd/2018_06_27/Com_tam_Muoi__Anh_thienlammwilly.jpg'),
(10, 'Món cơm và cháo', 'https://chonmuachuan.com/wp-content/uploads/2017/08/nau-chao-bang-noi-com-dien.jpg'),
(11, 'Món cuốn', 'https://kokotaru.com/wp-content/uploads/2009/07/F-cach-lam-cu-cai-cuon-thit-hap.jpg?189db0&189db0'),
(12, 'Món gỏi', 'http://ngaycuabep.com/wp-content/uploads/2014/06/image37-520x245.jpg'),
(13, 'Món hầm', 'http://silo.vn/wp-content/uploads/2015/12/cach-lam-da-day-ham-tieu-xanh-3-425x225.jpg'),
(14, 'Món hấp', 'http://mayfoods.vn/wp-content/uploads/2017/11/bi-kip-de-co-cac-mon-hap-ngon-ngot-2.jpg'),
(15, 'Món khác', 'http://cungbanvaobep.com/wp-content/uploads/2015/12/cach-nau-mon-bun-rieu-cua-chay-ngon-dung-dieu-cho-bua-sang-1.jpg'),
(16, 'Món lẩu', 'https://www.foodpanda.vn/wp-content/uploads/2018/10/lau-nam-ashima-7.jpg'),
(17, 'Món lẩu', 'https://www.foodpanda.vn/wp-content/uploads/2018/10/lau-nam-ashima-7.jpg'),
(18, 'Món luộc', 'http://bsdinhduong.vn/wp-content/uploads/2017/11/rau2-e1510824932790.jpg'),
(19, 'Món nướng', 'http://vanhoecolodge.com/wp-content/uploads/2018/07/ga-nuong.jpg'),
(20, 'Món xào', 'https://i.ytimg.com/vi/a4AQKd29DFc/maxresdefault.jpg'),
(21, 'Món chua', 'https://i.ytimg.com/vi/h0P5mHIiOCI/maxresdefault.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `id` int(11) NOT NULL,
  `customer` int(11) NOT NULL,
  `food` int(11) NOT NULL,
  `restaurant` int(11) DEFAULT NULL,
  `text` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `customer`, `food`, `restaurant`, `text`) VALUES
(1, 1, 5, NULL, 'Hello tôi là Ngọc ngố đây, tôi ăn bánh này và tôi thấy ngon. Các bạn cũng nên ăn nhé ahihi.'),
(2, 1, 5, NULL, 'Lại là tôi Ngọc ngố đây, ở trên tôi nói đùa đấy bánh này ăn chán lắm :D.'),
(3, 1, 2, NULL, 'Ngon quá xá là ngon'),
(4, 1, 5, NULL, 'Hey ngon quá'),
(8, 1, 5, NULL, 'wow'),
(9, 1, 5, NULL, 'hihi'),
(10, 1, 5, NULL, 'tuyệt'),
(11, 1, 5, NULL, 'cool'),
(12, 1, 5, NULL, 'hihi'),
(13, 1, 5, NULL, 'hhhhh'),
(14, 1, 5, NULL, 'hiooo');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `userName` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `name` varchar(50) NOT NULL,
  `permission` int(11) NOT NULL,
  `isOnline` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `userName`, `password`, `name`, `permission`, `isOnline`) VALUES
(1, 'gao', '123456', 'gao ồ', 0, 0),
(2, 'ahihi', '111', 'rrrrr', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `food`
--

CREATE TABLE `food` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `image` text NOT NULL,
  `desc` text,
  `recipe` text,
  `category` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `food`
--

INSERT INTO `food` (`id`, `name`, `image`, `desc`, `recipe`, `category`) VALUES
(1, 'Cơm tấm Long Xuyên', 'https://images.foody.vn/res/g18/171230/prof/s576x330/foody-mobile-cach-uop-suong-nuong-338-635790517139650245.jpg', 'Dọc đường về Long Xuyên, du khách sẽ bắt gặp nhiều món ăn phổ biến như bún, phở, hủ tiếu, bánh canh… trong đó món cơm tấm Long Xuyên hẳn sẽ mang đến cho bạn một trải nghiệm thú vị về món ăn tưởng chừng quen mà lạ này.\r\n Khác với món cơm tấm Sài Gòn có miếng sườn to, phần thịt sườn trong cơm tấm Long Xuyên đều được cắt mỏng thành từng miếng nhỏ, vừa đẹp mắt lại vừa tiện lợi cho người ăn. Thịt sườn khi nướng cũng được cắt thành lát dài, ướp gia vị rồi mới đem nướng.\r\n Đặc biệt, ngoài phần thịt sườn, đĩa cơm tấm ở Long Xuyên còn có thêm món trứng kho như món thịt kho tàu, trứng có màu gạch tôm và thấm gia vị rất tuyệt. Khi bày trí trên phần cơm, trứng cũng được cắt thành từng lát mỏng giúp người ăn không có cảm giác ngán.\r\n Mỗi phần cơm tấm ở Long Xuyên dao động từ 20.000 đồng. Có dịp về Long Xuyên, bạn có thể ghé quán cơm tấm Cây Điệp (số 67 Lý Tự Trọng, phường Mỹ Long, TP Long Xuyên) để thưởng thức sự độc đáo của món cơm tấm này.', 'Nguyên liệu:\r\n\r\n- Cơm tấm\r\n Gạo tấm 500 gr\r\n Lá dứa 30 gr\r\n Muối 1/2 muỗng cà phê\r\n Dầu ăn 1 muỗng canh\r\n- Sườn nướng\r\n Thịt heo 300 gr\r\n Mật ong 60 gr\r\n Nước mắm 30 ml\r\n Nước cốt dừa 30 ml\r\n Dầu hào 30 gr\r\n Dầu ăn 10 ml\r\n Nước cốt chanh 10 ml\r\n Bột ngũ vị hương 2 gr\r\n- Trứng kho\r\n Trứng vịt 5 quả\r\n Đường thốt nốt 30 gr\r\n Hành tím 10 gr\r\n Nước dừa 400 ml\r\n Nước tương 20 ml\r\n Nước mắm 20 ml\r\n Dầu hào 20 gr\r\n Hoa hồi 1 cái\r\n Dầu ăn 2 muỗng canh.\r\n\r\n\r\nCách chế biến:\r\n\r\n Bước 1: Để có 1 đĩa cơm tấm ngon và đúng điệu trước tiên bạn phải lựa được đúng loại gạo tấm. Sau đó bạn đem vo qua rồi ngâm gạo 15 phút, vớt gạo ra để ráo nước. Chuẩn bị sẵn xửng hấp, nước sôi, cho gạo vào xửng, trộn thêm 1/2 muỗng cà phê muối, 1 muỗng canh dầu ăn và 30gr lá dứa để tăng mùi thơm làm cơm mềm và hạt bóng hơn. Đậy nắp hấp 30 phút cho cơm chín, sau đó xới đều.\r\n Bước 2: 300gr thịt heo (chọn thịt có xen kẻ mỡ) rửa sạch, cắt thành miếng mỏng dài, ướp thịt heo với 60gr mật ong, 30ml nước mắm, 30ml nước cốt dừa, 30gr dầu hào, 10gr dầu ăn, 2gr ngũ vị hương, trộn đều sau đó ướp thịt 30 phút cho thấm gia vị. Xiên thịt vào que, đặt từng xiên thịt lên bếp than nướng chín.\r\n Bước 3: Chuẩn bị món trứng kho. Đun nóng 2 muỗng canh dầu ăn, cho vào nồi 30gr đường thốt nốt, đun đến khi đường chuyển sang màu cánh gián thì cho 10gr hành tím băm vào phi thơm. Tiếp theo đổ vào nồi 400ml nước dừa, 20ml nước tương, 20ml nước mắm, 20gr dầu hào, 3-4 nhánh hoa hồi (khoảng 1 hoa) rồi khuấy đều. Đun cho nước sôi lên thì cho vào 5 quả trứng vịt đã luộc chín và lột vỏ, kho liu riu cho nước keo lại, trứng chuyển màu cánh gián đẹp mắt là được.\r\n Bước 4:Khi ăn cắt thịt và trứng luộc ra thành những miếng nhỏ. Ngoài ra bạn cũng có thể chuẩn bị thêm bì và đồ chua để cho món cơm tròn vị hơn.', 9),
(2, 'Bún cá Châu Đốc', 'https://www.hoidaubepaau.com/wp-content/uploads/2018/08/bun-ca-chau-doc.jpg', 'Nhắc đến bún cá, người ta thường nhớ ngay đến món bún cá Châu Đốc trứ danh. Chẳng ai biết xuất xứ của món ăn này, chỉ biết rằng bún cá là một món ăn rất được người dân miền Tây yêu thích. Có lẽ một phần cũng vì hình ảnh miền Tây thường gắn liền với sông nước, nhiều tôm cá nên các món ăn được chế biến từ cá luôn chiếm giữ một vai trò quan trọng trong văn hóa ẩm thực.', 'Nguyên liệu:\r\n\r\nSơ chế nguyên liệu\r\n – 1 con cá lóc (khoảng 500g – 1kg)\r\n – 500g xương heo\r\n – 300g củ ngải bún (nguyên liệu bắt buộc để tạo nên hương vị đặc trưng cho món bún cá miền Tây)\r\n – 100g bột nghệ\r\n – 100g nghệ tươi\r\n – 300ml giấm\r\n – 1 trái chanh\r\n – 4 muỗng canh mắm ruốc\r\n – 4 muỗng canh nước mắm\r\n – Sả, ớt, tỏi băm: mỗi thứ 3 muỗng canh\r\n – Gia vị: muối, đường, bột ngọt, hạt nêm, dầu ăn…\r\n – Rau ăn kèm: bông điên điển, rau chuối bào, giá đỗ, rau muống, húng cây…\r\n – Bún tươi\r\n\r\n\r\nCách chế biến:\r\n\r\nBước 1: Xương heo rửa sạch, cho vào nồi nước sôi, luộc khoảng 10 phút, dùng muỗng hớt bọt bẩn trong khi luộc.\r\n Bước 2: Cá lóc móc bỏ vảy, mang, ruột cá rồi dùng muối và giấm chà lên thân cá, rửa cho sạch nhớt, rửa sạch lại lần nữa với nước.\r\n Bước 3: Củ ngải bún, nghệ tươi, sả rửa sạch. Sả đem cắt khúc và đập dập. Phần ngải bún và nghệ tươi, bạn giã nhuyễn và hòa với nước lọc, lọc lấy phần nước, bỏ xác đi.\r\n Bước 4: Phần mắm ruốc bạn gói vào lá chuối và đem nướng cho thơm, sau đó dùng rây lọc lại cho bớt cặn.\r\n Luộc lấy thịt cá, nấu nước xương heo\r\n Bước 1: Cá sau khi làm sạch, bạn cho vào nồi luộc chín trong khoảng 10 – 20 phút. Sau đó, tắt bếp, chờ cá nguội bớt thì tách bỏ xương, lấy phần thịt cá.\r\n Lưu ý: Nước luộc cá bạn giữ lại và đừng bỏ đi nhé!\r\n Bước 2: Xương heo sau khi luộc sơ, bạn vớt ra cho vào nồi nước luộc cá và nấu khoảng 30 – 45 phút để nước dùng ngon ngọt. Trong quá trình nấu, bạn cho sả đập dập vào để nước được thơm.\r\n Nấu bún cá Châu Đốc\r\n Bước 1: Phần thịt cá đã bỏ xương, bạn ướp cùng bột nêm, muối, đường, bột nghệ, bột ngọt. Ứơp khoảng 10 phút, bạn cho phần cá lên chảo xào đều cho nóng và ngấm gia vị.\r\n Bước 2: Bắt lại nồi nước dùng nấu với xương heo khi nãy cho nóng. Khi nước sôi, cho tất cả phần cá xào, mắm ruốc, sả, tỏi, nước ngải bún + nghệ tươi vào. Tiếp tục nấu cho sôi rồi nêm lại gia vị cho vừa miệng là bạn đã hoàn thành nồi nước dùng trong cách nấu bún cá Châu Đốc này rồi.', 3),
(3, 'Canh chua cá linh bông điên điển', 'https://beptruong.edu.vn/wp-content/uploads/2018/03/ca-linh-non.jpg', 'Món này là kinh điển của miền Tây. Nó đặc biệt vì thành phần món ăn là các nguyên liệu rất đặc trưng của miền Tây: bông Điên Điển, bông Súng / So Đũa, cá Linh. \r\n Món ăn còn quý ở một lý do là cá Linh chỉ có vào mùa nước nổi (tháng 7-9 âm lịch). Nấu món này đúng điệu phải là cá Linh non, có vào đầu mùa nước nổi. Cuối mùa cá lớn, nhiều xương, người dân mang đi làm mắm cá Linh - cũng là món ngon nức tiếng vùng sông nước. \r\n Mấy năm nay lũ không về nên cũng chẳng có cá Linh. Năm nay lũ về sớm, dân miền Tây mừng lắm. Tôm cá về, nước ngọt giàu phù xa về, và cũng mang theo về những món ăn dân dã gây thương nhớ.', 'Nguyên liệu:\r\n\r\n- 500 gr cá Linh non, loại bằng 1 ngón tay\r\n- 300 gr bông Điên Điển\r\n- 150 gr bông Súng / So Đũa\r\n- 8 trái me non, 2 quả cà chua\r\n- 1 muỗng cà phê bột nêm\r\n- 2,5 muỗng cà phê đường (tùy thích)\r\n- 2,5 muỗng cà phê nước mắm ngon\r\n- Ớt, tỏi, hành củ, quế, ngò gai.\r\n\r\n\r\nCách chế biến:\r\n\r\nCá linh đánh bắt được hay mua ở chợ về phải lựa cá thật tươi (cá linh non càng ngon, vì có thể ăn luôn cả xương!). Làm cá chỉ cần móc hầu, bỏ ruột, cắt vây, kỳ, đuôi (không cắt đầu, đánh vảy), rửa sạch, để ráo. Bông điên điển lặt cuống, rửa sạch để ra rổ. Đổ nước vào nồi nấu sôi. Cơm mẻ (hay me chín cũng được) cho vào vợt lược, nhúng vào nồi. Dùng vá nghiền cho cơm mẻ hòa tan. Nêm gia vị (muối, đường, bột ngot…) cho vừa khẩu vị. Tăng lửa cho nước sôi bùng lên, thả cá linh vào, chờ cá chín thả tiếp bông điên điển vào, nêm lần cuối và nhấc xuống ngay (đừng để bông điên điển chín mềm mất ngon). Xắt nhuyễn lá ngò om (hoặc ngò gai), cho thêm vài lát ớt sừng chín vào nồi là xong.', 4),
(4, 'Gỏi Cá Mai Vũng Tàu', 'https://images.foody.vn/res/g2/17786/prof/s576x330/foody-mobile-15-jpg-947-636120617121819985.jpg', 'Trong ẩm thực ở Vũng Tàu, Gỏi Cá Mai là một trong những món đặc sản có thể nói là đứng đầu danh sách những món ăn hấp dẫn nhất của thành phố biển này. Giống như cá cơm, thân nhỏ màu trắng và ít tanh nên cá mai rất được ưa chuộng để dùng chế biến những món ăn khá hấp dẫn đặc biệt là làm gỏi.\r\n\r\nĐối với dân miền biển Vũng Tàu, một trong các bí quyết để có món gỏi cá mai ngon không gì hơn ngoài điều cực kỳ đơn giản là cá mai càng tươi bao nhiêu, thì món gỏi càng ngon bấy nhiêu. Cá khi đánh bắt về được làm sạch vảy, rút xương ướp gia vị để làm chín cá. Những gia vị ướp cá tươi để làm gỏi không phức tạp, mà đơn giản như những món gỏi cá khác như giấm, tỏi, chanh và ớt. Tuy nhiên quan trọng nhất vẫn là cách nêm nếm sao cho chuẩn vị nhất.', 'Nguyên liệu:\r\n\r\n– Cá mai tươi 200 gram\r\n– Hành tây: 1 củ (khoảng 50 gram)\r\n– Ớt xiêm tươi, tỏi, sả, chanh, dưa leo.\r\n– Bánh tráng gạo để cuốn: 1 xấp\r\n– 1 chút thính được làm từ gạo rang giã ngỏ\r\n– Xà lách, ngò rí, diếp cá, tía tô.\r\n\r\n\r\nCách chế biến:\r\n\r\n– Đánh vảy cá mai, rửa sạch cá, sau đó cắt bỏ đầu và đuôi. Để cá lên thớt, khéo léo dùng dao lấy phần xương cá, bỏ đi. làm lần lượt đến hết.\r\n– Cho phần thân cá làm sạch vào tô, cho tiếp nước cốt chanh vào tô. Trộn đều để chanh làm cá chín tái sơ.\r\n– Cạo vỏ gừng, rửa sạch. Xắt gừng thành sợi mỏng.\r\n– Lột vỏ hành tây, xắt thành khoanh mỏng.\r\n– Bỏ bớt phần lá già của sả, xắt mỏng.\r\n– Xếp cá tái lên đĩa, cho gừng, hành tây, sả lên trên. Rắc đậu phộng, hành phi và 1 chút thính lên.\r\n– Công thức pha chế nước chấm gỏi cá mai Vũng Tàu\r\nCho khoảng 30 gram me chín và 30 ml nước sôi vào chén, dùng muỗng dầm nhẹ cho me ra nước cốt. Dùng rây lọc lấy lấy nước cốt me. Cho 1/2 muỗng cà phê ớt bằm + 1/2 muỗng cà phê tỏi bằm + 1 muỗng canh nước mắm nhỉ + 1 muỗng canh đậu phộng giã mịn + 1 một muỗng canh đường + 1/4 muỗng cà phê bột ngọt + nước cốt me vào chén. Trộn đều hỗn hợp trên để thành nước chấm ăn kèm.\r\nRau sống nhặt và rửa sạch để ráo nước. Khi ăn, trộn cá cùng hành tây, sả, gừng rồi mới cuốn cá cùng rau sống, bánh tráng. Chấm kèm với nước chấm đã chuẩn bị sẵn bạn sẽ cảm nhận được hương vị hấp dẫn khó tả của món ăn này.', 12),
(5, 'Bánh Xèo Long Hải', 'https://images.foody.vn/res/g22/212063/prof/s576x330/foody-mobile-banh-xeo-giahan1-jpg-169-635914732148488742.jpg', 'Đến với vùng biển Long Hải, du khách sẽ được thưởng thức món bánh xèo thơm ngon hấp dẫn. Nhờ nguồn thực phẩm tươi nguyên, mua từ người dân chài đánh bắt trong ngày mang về chưa qua ướp đá, làm lạnh, đồng thời kỹ thuật đổ bánh sao cho giòn, bánh có màu vàng tươi, thơm phức tạo nên điểm hấp dẫn nổi bật của bánh xèo Long Hải. Bên cạnh đó nước chấm được pha chế công phu, vị chua chua ngọt ngọt của dưa cải, vị đậm đà của nước mắm ngon loại đặc biệt còn giúp người ăn có thể cảm nhận được hương vị đặc trưng không lẫn vào đâu được', 'Nguyên liệu:\r\n\r\n- Bột bánh xèo pha sẵn (nếu không có bạn dùng bột gạo trộn với bột nghệ và cốt dừa)\r\n- Bia (giúp vỏ bánh giòn lâu)\r\n- Thịt ba chỉ\r\n- Tôm nõn (có thể thay thế bằng tép)\r\n- Đỗ xanh nguyên vỏ\r\n- Hành tây, hành lá, hành khô, giá đỗ, cà rốt, ớt tươi, chanh, tỏi.\r\n- Rau sống\r\n- Muối, hạt tiêu, đường\r\n\r\n\r\nCách chế biến:\r\n\r\nSơ chế nguyên liệu:\r\n- Thịt ba chỉ thái mỏng hoặc bằm sơ, ướp gia vị, hành khô. Tôm cắt râu, làm sạch đem xào chín cùng thịt.\r\n- Hành tây bóc vỏ thái múi cau ngâm nước đá. Giá đỗ rửa sạch để ráo nước. Cà rốt thái hoa đẹp mắt. Hành lá xắt nhỏ.\r\n- Đỗ xanh đồ chín.Pha nước chấm:\r\n- Nước chấm pha chua ngọt theo tỷ lệ: 5 nước, 2 đường, 1.5 mắm, 1 nước cốt chanh rồi thêm tỏi, ớt băm vào.\r\nĐổ bánh xèo:\r\n- Trộn bột với một thìa nhỏ muối, một chén bia và nước. Khuấy đều đến khi có hỗn hợp lỏng, không vón cục. Thả hành lá và đỗ xanh vào. Để bột nghỉ khoảng 20 phút. Không nên pha bột đặc khiến vỏ bánh dày, không giòn, khó chín.\r\n- Cho một thìa dầu ăn vào chảo chống dính, chờ dầu sôi. Đổ một thìa bột vào láng cho thật mỏng. Đậy vung khoảng 1 phút cho chín. Cho hỗn hợp nhân tôm thịt và đỗ giá sống lên. Gập đôi bánh và chờ cho giòn đều 2 mặt.\r\n- Gắp bánh ra đĩa, thưởng thức nóng. Khi ăn cuộn bánh xèo vào bánh tráng hoặc lá cải xanh, thêm rau sống, chấm nước chua ngọt.', 2),
(6, 'Gỏi Sứa', 'https://images.foody.vn/res/g18/177594/s750x750/d3cf36c5-019a-47ea-9e51-309d84bf2517.jpg', 'Đây là món ăn lạ miệng rất thích hợp trong mùa nắng nóng, vừa ngon, vừa mát, lại rẻ tiền.\r\nCon sứa trông giống như thực vật nhưng lại là động vật thuộc loại xoang trường cùng họ với san hô. Thân sứa trong suốt như thủy tinh, mềm mại như chiếc lá, mang đủ mầu sắc, từ xanh dương, hồng cho đến tim tím… Sứa chứa 95% nước biển, nếu đem phơi nắng suốt 12 giờ liền trên bãi cát thì toàn thân sứa sẽ mỏng ra như tờ giấy.\r\nNộm gỏi sứa không chỉ hấp dẫn với vị giòn sần sật của từng miếng sứa hòa quyện với gia vị chua ngọt, cay cay đã tạo nên hương vị khó quên ở đầu lưỡi mà sau khi thưởng thức đầu tiên cũng dễ khiến người thưởng thức thích thú.', 'Nguyên liệu:\r\n\r\n- 350gr sứa tươi ngon \r\n- 100gr lạc - 25gr mè trắng \r\n- 2-3 dưa chuột \r\n- 150gr giá đỗ \r\n- Hành tây, cà rốt \r\n- Sả, lá chanh, chanh \r\n- Húng quế \r\n- Đường, bột canh, ớt, dầu vừng\r\n\r\n\r\nCách chế biến:\r\n\r\nBước 1: Sơ chế nguyên liệu\r\nCách sơ chế sứa: đầu tiên rất quan trọng vì sứa không làm sạch sẽ gây cảm giác ngứa hoặc dị ứng khi ăn. Nếu bạn mua gói sứa làm sẵn thì khi thực hiện cách làm gỏi sứa sẽ đơn giản. Nhưng nếu bắt được sứa hay mua loại chưa chế biến thì các bạn cần thực hiện các bước sau:\r\n- Sứa rửa sạch, mổ loại hết chất độc. Sau khi cắt thành từng miếng rửa cho sạch nhớ thì ngâm thịt sứa với nước muối phèn 4-5 lần để giảm thủy phần, giúp sứa ngon hơn và không bị teo tóp.\r\n- Hoặc nếu không thì ngâm sữa với nước lá lăng, vỏ sú vẹt, củ nâu, nước lá ổi để làm sạch.\r\n- Thịt sứa thái nhỏ rửa bằng nước ấm, có thể ngâm với vài lát gừng cho bớt mùi tanh. Sau đó, bạn bắt tay vào thực hiện món gỏi.\r\n- Lạc và vừng rang chín, xát sạch vỏ và giã nhỏ lạc.\r\n- Cà rốt gọt vỏ, rửa sạch và thái chỉ.\r\n- Dưa leo rửa sạch, nạo vỏ và bổ đôi, thái mỏng vừa ăn.\r\n- Hành tây lột vỏ rửa sạch, thái lát mỏng, ngâm cùng nước pha giấm 15 phút sau đó vớt ra để ráo nước.\r\nBước 2: Nộm sứa\r\nTiếp theo, chúng ta tới cách làm nộm sứa ngon và chuẩn nhất. Đây là công đoạn quan trọng nhất.\r\n- Tỉ lệ thành phần gia vị: Đường, bột canh, dầu mè với tỉ lệ - 1:1:1, cho các nguyên liệu vào bát con đảo đều cho tan hết đường. Sau đó, trộn nộm, cho các nguyên liệu gồm sứa, giá đỗ, hành tây, cà rốt, lá chanh, sả, húng quế, ớt vào chén rồi đổ bát nước chấm vừa pha vào trộn đều lên. Để trong khoảng 10 – 15 phút cho sứa ngấm gia vị là có thể thưởng thức rồi.\r\n- Trước khi ăn các bạn hãy rắc thêm lạc giã nhỏ cùng các loại rau thơm đã chuẩn bị và tùy thích để món ăn thêm hấp dẫn nhé.', 12);

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `food` int(11) NOT NULL,
  `restaurant` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`food`, `restaurant`) VALUES
(5, 3),
(2, 2),
(3, 2),
(3, 2),
(2, 3);

-- --------------------------------------------------------

--
-- Table structure for table `price`
--

CREATE TABLE `price` (
  `id` int(11) NOT NULL,
  `food` int(11) NOT NULL,
  `restaurant` int(11) NOT NULL,
  `price` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `price`
--

INSERT INTO `price` (`id`, `food`, `restaurant`, `price`) VALUES
(1, 5, 1, 70000),
(2, 2, 2, 40000),
(3, 3, 3, 70000);

-- --------------------------------------------------------

--
-- Table structure for table `province`
--

CREATE TABLE `province` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `info` text,
  `latLong` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `province`
--

INSERT INTO `province` (`id`, `name`, `info`, `latLong`) VALUES
(1, 'An Giang', 'An Giang là vùng đất hiền hòa với phong cảnh thiên nhiên hữu tình mộng mơ. An Giang còn là nơi giao thoa văn hóa, là nơi hội tụ nét đặc sắc của ẩm thực miền Tây nam bộ và vùng ven biên giới Cam-Pu-Chia. Trong bài viết dưới đây, Thổ địa An Giang sẽ mách cho bạn những địa điểm ăn ngon và giá rẻ khi du lịch An Giang.', NULL),
(2, 'Bà Rịa - Vũng Tàu', 'Tỉnh Bà Rịa – Vũng Tàu không chỉ lôi cuốn du khách gần xa bởi cảnh quan tươi đẹp mà còn bởi có nhiều món ăn hấp dẫn, hiếm nơi nào có được. Mỗi món ăn ở đây đều gắn liền với từng địa danh, xuất xứ và mang những hương vị đặc trưng riêng. Một số đặc sản như: Bánh hỏi An Nhất, Mứt hạt bàng Côn Đảo, Gỏi cá mai, Bánh xèo biển Long Hải, Lẩu súng Phước Hải, Tiết canh tôm hùm Long Hải, Bánh khọt Vũng Tàu, Cháo Hàu Long Sơn, Bánh bông lan trứng muối.', NULL),
(3, 'Bắc Giang', 'Bắc Giang xứ sở của những quả vải Lục Ngạn nổi tiếng và còn vô vàn những món ngon khác đang chờ bạn khám phá. Nếu có dịp đến với đất Kinh Bắc, bên cạnh việc thăm thú những di tích lịch sử, văn hóa lừng danh như di tích Yên Thế, chùa Vĩnh Nghiêm…hay các danh lam thắng cảnh tuyệt đẹp, thì du khách cũng không nên bỏ qua những đặc sản của vùng đất có văn hóa lâu đời.Để phong phú cho những bữa ăn hàng ngày, từ xa xưa ông cha ta đã chế biến sáng tạo ra nhiều loại món ăn khác nhau từ những sản phẩm đồng quê dân dã, ngày càng làm sinh động hấp dẫn thêm cho kho tàng ẩm thực của người Việt Nam.', NULL),
(4, 'Bắc Kạn', 'Ngoài cảnh đẹp non nước hữu tình của hồ Ba Bể, Bắc Kạn còn có những đặc sản dân tộc đặc trưng khiến bước chân lữ khách du lịch nơi đây phải lưu luyến. Đến với Bắc Kạn, các bạn không những có cơ hội tham quan khung cảnh thiên nhiên tuyệt đẹp mà còn được thưởng thức các loại đặc sản mang đậm bản sắc của đồng bào dân tộc nơi đây.', NULL),
(5, 'Bạc Liêu', 'Bạc Liêu nổi tiếng là nơi câu Vọng cổ đầu tiên được cất lên, đây cũng là nơi gắn liền với nhiều giai thoại về dân chơi nhà giàu thời đầu thế kỉ. Đến Bạc Liêu, bên cạnh việc ngắm nhìn đồng ruộng cò bay thẳng cánh, lắng nghe câu Vọng cổ mùi mẫn, du khách còn nên khám phá những món ăn đặc sắc ở nơi đây.', NULL),
(6, 'Bắc Ninh', 'Bắc Ninh là tỉnh có diện tích nhỏ nhất Việt Nam song lại có một nền văn hóa đặc sắc. Nơi đây hằng năm có trên 300 lễ hội dân gian, nhiều di tích lịch sử và những làng nghề truyền thống. Trong số rất nhiều những làng nghề truyền thống đó, khiến du khách đã thưởng thức rồi chẳng muốn về là quê hương của những làn điệu dân ca quan họ ngọt ngào, có những nơi đã sản sinh ra loại đặc sản thơm ngon độc nhất, níu chân du khách bởi những đặc sản ngon nức tiếng. Bắc Ninh có rất nhiều món ngon như: thịt chuột Đình Bảng, nem làng Bùi, gà Hồ, bánh phu thê…khiến bạn không thể cưỡng nổi khi tới nơi đây.', NULL),
(7, 'Bến Tre', 'Xứ dừa Bến Tre vốn nổi tiếng với những rặng dừa xanh mượt, trải dài tít tắp, với những con sông, kênh rạch chằng chịt và đặc biệt là những đặc sản gợi thương gợi nhớ. Bến Tre được biết tới là xứ sở của cây dừa, do đó mà ở đây có rất nhiều món ăn dân dã thơm ngon đang chờ đón bạn tới thưởng thức. Đến với xứ sở dừa Bến Tre, bạn sẽ có thể thưởng thức các món ăn ẩm thực Bến Tre vừa thơm ngon, lại vừa độc đáo và đặc trưng bởi đa phần đều được chế biến từ các nguyên phụ liệu của vùng đất Bến Tre. Sau đây, chúng tôi xin giới thiệu những đặc sản Bến Tre mà bạn nên nếm thử qua mỗi khi đến đây. Chắc chắn sẽ không làm bạn phải thất vọng.', NULL),
(8, 'Bình Định', 'Bình Định là một địa danh nổi tiếng với khách du lịch Việt Nam và quốc tế. Không chỉ gây ấn tượng bởi những danh lam thắng cảnh, di tích văn hóa đặc sắc, nơi đây còn \"quyến rũ\" bởi nền ẩm thực phong phú. Đến với miền đất võ, bạn sẽ có cơ hội được thưởng thức những món đặc sản vô cùng thơm ngon và bổ dưỡng. Là một trong những tỉnh thuộc dãy đất miền Trung, nền ẩm thực Bình Định được xem là một trong những nét tiêu biểu nhất của văn hóa ẩm thực Trung Bộ nói chung. Vậy điểm hấp dẫn trong ẩm thực Bình Định là gì? Cùng tìm hiểu trong bài viết sau đây nhé!', NULL),
(9, 'Bình Dương', 'Bình Dương luôn thu hút khách du lịch bởi những vườn trái cây trĩu quả và các món ăn ngon đậm đà bản sắc Nam Bộ. Những quán ăn ngon nức tiếng ở Bình Dương luôn là địa chỉ thu hút đông đảo thực khách đến đây thưởng thức các món hấp dẫn, độc đáo được chế biến theo những công thức đặc biệt. Bên cạnh những đặc sản thơm ngon nức tiếng, ở Bình Dương còn có vô vàn món ngon, \'ăn là ghiền\' khiến nhiều bạn trẻ \'xiêu lòng\'.', NULL),
(10, 'Bình Phước', 'Đến Bình Phước, người ta không chỉ được thăm thú những cảnh đẹp, tìm hiểu văn hóa người dân nơi đây mà còn bị say mê bởi những món đặc sản ngon không thể chối từ. Bình Phước nổi tiếng với những món ngon không trộn lẫn với những vùng quê khác như: gỏi trái điều, bánh hạt điều, hạt điều rang muối, ve sầu chiên giòn...và nhiều món ăn độc đáo khác.', NULL),
(11, 'Bình Thuận', 'Mảnh đất Bình Thuận có nhiều món ăn đặc sản cực ngon mà bất cứ du khách nào đã từng tới du lịch đều vương vấn. Du lịch Bình Thuận bạn sẽ được thưởng thức rất nhiều món đặc sản ngon ở vùng đất nắng và gió này. Những nguyên liệu quen thuộc như hải sản, bánh tráng, lòng heo... được người dân Bình Thuận chế biến ra những món ăn ngon trở thành đặc sản của riêng vùng đất này. Sau đây là những đặc sản Bình Thuận ngon nức tiếng mà bạn nên một lần thưởng thức nếu có dịp đặt chân tới đây nhé!', NULL),
(12, 'Cà Mau', 'Mũi đất Cà Mau không chỉ là “địa chỉ cuối cùng” của Tổ quốc, mà còn là xứ sở của rất nhiều món ngon làm say mê thực khách bốn phương. Du lịch đến Cà Mau, ngoài việc thưởng ngoạn cảnh đẹp, Quý khách hãy dành chút thời gian để khám phá ẩm thực Cà Mau. Miền đất mũi mặn mòi này có vô số các sản vật từ biển, từ rừng hút hồn du khách. Nếu có dịp ghé Cà Mau, hãy thưởng thức hết các đặc sản mà thiên nhiên đã ban tặng riêng cho vùng đất địa đầu Tổ quốc. Tôi tin chắc rằng, nếu Quý khách đã không thử thì thôi, thử rồi sẽ cảm thấy thích thú và còn muốn thưởng thức thêm nhiều lần nữa. Ngay cả những người không phải đến Cà Mau để du lịch mà đi công tác, công việc, học hành, thăm viếng, họ cũng tranh thủ thời gian để được thưởng thức 1 vài trong số rất nhiều những món ăn dân dã ngon ở Cà Mau.', NULL),
(13, 'Cao Bằng', 'Cao Bằng gạo trắng nước trong! Đến Cao Bằng, được thưởng thức những đặc sản do người dân vùng núi rừng Đông Bắc thân thiện và vô cùng mến khách chế biến sẽ khiến ai đã một lần là nhớ mãi...Còn gì tuyệt vời hơn khi du lịch đến chốn núi rừng Tây Bắc – Cao Bằng gạo trắng nước trong để thưởng thức ngay những đặc sản của người dân nơi đây. Vùng đất Cao Bằng có quá nhiều những món ngon đặc sản khiến bạn khó có thể quên. Có những món bạn nên thưởng thức ngay trong chuyến đi nhưng có những món bạn có thể đem về làm quà cho bạn bè, người thân.', NULL),
(14, 'Đắk Lắk', 'Khi nhắc tới  Đắk Lắk nhiều người chỉ nhớ tới núi rừng Tây Nguyên hùng vĩ, đất đỏ bazan màu mỡ, lễ hội đua voi tưng bừng… mà bỏ quên một nét văn hóa cũng đặc sắc không kém. Đắk Lắk nói riêng và vùng Tây Nguyên nói chung không chỉ hấp dẫn du khách bởi cảnh quan thiên nhiên hùng vĩ mà còn bởi những món đặc sản dân tộc độc đáo làm say lòng du khách. Đắk Lắk giữ trong mình một nền văn hóa ẩm thực phong phú, là kết tinh của các dân tộc. Về đây, du khách sẽ được thưởng thức đủ món ăn ngon như: gà sa lửa, cơm lam, canh chua nấu cá lăng, gỏi rau rừng, gỏi cà đắng...', NULL),
(15, 'Đắk Nông', 'Đến Đắk Nông, không chỉ ngắm những ngọn thác tuyệt đẹp hùng vĩ hay khám phá hệ sinh thái độc đáo của núi rừng. Bạn cũng sẽ được thưởng thức những món ăn đậm chất Tây Nguyên. Mỗi khi nhắc đến Đắk Nông chắc hẳn du khách sẽ nghĩ ngay đến cảnh hoang sơ, hùng vĩ của những thác nước đang ngày đêm ầm ầm tuôn chảy xen lẫn trong những cánh rừng xanh bạc ngàn cùng với nền văn hoá lâu đời của người M’nông. Trong đời sống sinh hoạt của đồng bào M’nông ẩm thực lại ẩn chứa sự độc đáo, mới lạ mang đến cho du khách những dấu ấn không thể nào quên khi ghé thăm vùng đất này, những món ăn phải kể đến là: Rượu cần, cơm lam, canh thụt. Những đặc sản nổi tiếng như cà phê Đức Lập, khoai lang Tuy Đức, trái cây Đắk Glong, cá lăng sông Sêrêpốk, canh thụt đọt mây… mà du khách không nên bỏ lỡ khi có dịp đặt chân đến Đắc Nông.', NULL),
(16, 'Điện Biên', 'Không quá phong phú trong chủng loại nhưng các món ăn ở Điện Biên cực kì độc đáo, có một không hai khiến du khách đến từ đâu cũng sẽ hài lòng. Các món ăn của người dân tộc ở Điện Biên được chế biến rất cầu kỳ, nhất là nghệ thuật sử dụng các loại gia vị độc đáo của núi rừng Tây Bắc. Xôi nếp nương, thịt trâu hun khói, chẳm chéo, bắp cải cuốn nhót xanh, sâu chít, pa pỉnh, măng đắng, rau hoa ban, gạo tám… là những món ngon Điện Biên không lẫn vào đâu được. hay hoa ban là những món ăn đặc sắc khi đặt chân đến vùng đất Điện Biên Phủ lịch sử. Đừng mải vui chơi với những di tích lịch sử, danh lam thắng cảnh ở Điện Biên mà bỏ quên đi rất nhiều món ăn ngon ở Điện Biên các bạn nhé.', NULL),
(17, 'Đồng Nai', 'Những món đặc sản Đồng Nai luôn có sức hút lạ kì đối với du khách. Hơn nữa do ở ngay gần Sài Gòn nên đây cũng là địa điểm được nhiều người lựa chọn  cho những chuyến du lịch cuối tuần. Đồng Nai có những món ăn nghe khá dân dã nhưng độ ngon thì khỏi phải bàn, rất phong phú như bưởi Tân Triều, mít Tố Nữ, nấm mối, cơm gà cá mặn… làm mê mẩn du khách gần xa khi có dịp đặt chân đến nơi đây. Một khi bạn đã đến Đồng Nai, bạn chẳng thể bỏ qua được những món ngon dân dã này. Và khi đã rời khỏi vùng đất này, bạn cũng không thể không đem theo những thứ đặc sản ấy về làm quà.', NULL),
(18, 'Đồng Tháp', 'Đồng tháp là một tỉnh thuộc vùng đồng bằng sông cửu long, nổi tiếng với sản vật miền sông nước, vì thế những món ngon nhất của Đồng Tháp gắn liền với mùa nước nổi ở vùng này. Nhắc đến Đồng Tháp hẳn ai cũng nghĩ đến vựa lúa lớn của cả nước, là “vùng đất sen hồng” với những cánh đồng sen bạt ngàn mà ít ai biết rằng Đồng Tháp còn là “không gian ẩm thực miền Tây” với những món ăn dân dã vô cùng độc đáo và hấp dẫn. Đến thăm Đồng Tháp Mười bạn không chỉ được tận hưởng vẻ đẹp của thiên nhiên với những cánh rừng tràm bạt ngàn, những hồ sen bạt ngàn, những vườn cò sân chim hoang sơ của miệt vườn đặc sắc, mà còn có cơ hội được thử những món ngon đặc sắc đầy hương vị đồng quê như cá lóc nướng lá sen, ốc treo giàn bếp, chuột đồng quay lu, nem Lai Vung, hủ tiếu Sa Đéc ….Sau đây là những món ngon mùa nước nổi bạn không nên bỏ lỡ khi đến với Đồng Tháp.', NULL),
(19, 'Gia Lai', 'Những đặc sản Gia Lai nổi tiếng làm nên nét văn hóa ẩm thực đặc sắc gây thương nhớ cho khách du lịch. Ở nơi đây bạn không chỉ được ngắm những khu rừng nguyên sinh rộng mênh mông xanh bạt ngàn mà du khách còn được thưởng thức rât nhiều món ăn ngon. Gia Lai được thiên nhiên ưu ái ban tặng những cánh rừng nguyên sinh tuyệt đẹp, nơi đây không chỉ đẹp mà còn mang trong mình những loại đặc sản “ngon tuyệt cú mèo”, làm nên vẻ đẹp ẩm thực Gia Lai như: măng rừng, rau rừng, gà rừng, mật ong rừng… Ngoài ra Gia Lai còn có một số những món đặc sản nổi tiếng khác như: Phở khô Gia Lai, bún mắn cua, muối kiến vàng, cà phê Pleiku, bò một nắng…', NULL),
(20, 'Hà Giang', 'Hà Giang được biết đến là một tỉnh miền núi với nhiều phong cảnh hùng vĩ, phong tục tập quán, lễ hội phong phú. Đến Hà Giang, người ta không chỉ truyền tai nhau về cao nguyên đá, về những cánh đồng tam giác mạch hay những cung đường phượt mà còn về những món ăn khó quên. Ẩm thực Hà Giang rất phong phú và độc đáo với những món ăn lạ, mang hương vị núi rừng luôn để lại ấn tượng khó quên trong lòng du khách như: Thắng cố, bánh tam giác mạch, bánh cuốn hay mật ong bạc hà là những món ăn khiến người ta mê đắm ở vùng đất Hà Giang.', NULL),
(21, 'Hà Nam', 'Hà Nam là một tỉnh nằm ở Tây Nam châu thổ sông Hồng trong vùng kinh tế trọng điểm bắc bộ và là cửa ngõ phía Nam của thủ đô Hà Nội. Tỉnh Hà Nam có vùng đồng bằng với  diện tích đất đai màu mỡ, bãi bồi ven sông Hồng, sông Châu, là tiền đề để phát triển sản xuất nông nghiệp hàng hoá, công nghiệp chế biến nông sản thực phẩm và du lịch sinh thái. Với địa hình và đa dạng về các loại cây trồng cũng như hệ sinh thái người dân Hà Nam đã tận dụng những gì tạo hóa ban tặng để chế biến ra các món ăn ngon nổi tiếng trên khắp cả nước và trở thành Những Đặc sản không thể bỏ qua khi ghé thăm Hà Nam.', NULL),
(22, 'Hà Tĩnh', 'Hà Tĩnh là mảnh đất nổi tiếng với nhiều điểm du lịch hấp dẫn. Không những thế, bất cứ du khách nào khi đến đây sẽ đều bị thu hút bởi những món ăn ngon. Mực \'nhảy\' Vũng Áng, thịt dê núi Hương Sơn, bưởi Phúc Trạch, bánh đa vừng... là những đặc sản trứ danh Hà Tĩnh mà bạn không nên bỏ qua nếu có dịp ghé đến mảnh đất Hồng Lam thân thiện và hiếu khách.', NULL),
(23, 'Hải Dương', 'Hải Dương là vùng đất bình dị, gần gũi và các món ngon nơi đây cũng thấm đượm tinh thần quê hương. Những món đặc sản tuy giản dị nhưng mang một hương vị ẩm thực riêng mà không nơi nào có được khiến cho thực khách nào đã từng thử qua đều phải thấy nao lòng. Nhắc đến Hải Dương, người ta không thể không nhắc đến những món ngon của vùng đất này như vải thiều Thanh Hà, bánh đậu xanh, gà Mạnh Hoạch hay bánh đa gấc…', NULL),
(24, 'Hậu Giang', 'Tỉnh Hậu Giang nổi tiếng không chỉ với vùng đất phì nhiêu rất có tiềm năng về du lịch sinh thái mà cũng từ nhiều món đặc sản ngon đặc trưng vùng sông nước.\r\nNếu có dịp đặt chân đến Hậu Giang, du khách hãy thử qua những đặc sản sau đây như: cháo lòng Cái Tắc, chả cá thác lác Hậu Giang, bưởi năm roi Phú Hữu, bún gỏi già… Các bạn sẽ rất ngạc nhiên về hương vị thơm ngon của những món ăn dân dã này đấy.', NULL),
(25, 'Hòa Bình', 'Không chỉ có đập thủy điện, Hòa Bình còn nổi tiếng với phong cảnh núi rừng hùng vĩ, vừa hoang sơ kì vĩ, vừa nên thơ hữu tình đặc biệt hơn là các món ăn mang đậm hơi thở của núi rừng Tây Bắc như: Cơm đồ, nhà gác, nước vác, lợn thui,... Những du khách đã từng một lần thăm thú Hòa Bình đều không khỏi trầm trồ ngỡ ngàng và ấn tượng sâu sắc bởi hương vị đậm đà quyến rũ của những món ăn mang đậm nét đặc trưng vùng Tây Bắc nơi đây.', NULL),
(26, 'Hưng Yên', '\'Thứ nhất kinh kì, thứ nhì Phố Hiến\' - chẳng phải ngẫu nhiên mà người ta phong cho Hưng Yên cái tên như vậy. Nơi đây không chỉ nổi tiếng với những di tích lịch sử văn hóa lâu đời mà còn nổi tiếng bởi những món đặc sản từ dân dã đến công phu \'tiến vua\'. Hưng Yên là một vùng đất nổi tiếng với nhãn lồng, không những thế nơi đây còn nhiều đặc sản khác cũng hấp dẫn không kém như chả gà Tiểu Quan, ếch om Phượng Tường, bên cạnh đó còn có Gà Đông Tảo tiến vua...', NULL),
(27, 'Khánh Hòa', 'Là một tỉnh ven biển có nhiều làng chài nên phong cách ẩm thực ở Khánh Hòa chịu ảnh hưởng sâu sắc từ biển. Khánh Hòa nổi tiếng với những món đặc sản sang chảnh như yến sào hòn Nội, tôm hùm Bình Ba, nai khô Diên Khánh hay sò huyết Thủy Triều…nhưng cũng có rất nhiều món ngon dân dã như bánh tráng xoài, nem nướng Ninh Hòa, bánh căn, bánh ướt…vô cùng hấp dẫn.', NULL),
(28, 'Kiên Giang', 'Kiên Giang là vùng đất xinh đẹp luôn hấp dẫn mọi du khách ghé qua không chỉ bởi những hòn đảo thơ mộng, những phong cảnh hữu tình mà còn bởi những món ăn ngon nổi tiếng. Kiên Giang có nhiều món ăn ngon lạ, dân dã, mang hương vị riêng biệt không lẫn với bất kì nơi nào, đủ sức làm say lòng du khách thập phương. Kiên Giang có một nền ẩm thực vô cùng phong phú với rất nhiều món ăn ngon. Khi đến du lịch Kiên Giang, bạn hãy cố gắng thử hết các món ăn này nhé!', NULL),
(29, 'Kon Tum', 'Kon Tum, ngoài khám phá cảnh đẹp thiên nhiên, bạn cũng nên thưởng thức các món ăn ngon tại vùng đất này. Cũng giống như các thành phố khác, ẩm thực Kon Tum rất đa dạng và phong phú, tại đây có rất nhiều món ăn ngon, hấp dẫn cho bạn tha hồ thưởng thức. Tuy nhiên, để giúp bạn có thể thưởng thức các món ăn ngon, chuẩn vị nhất thì sẽ gợi ý cho bạn một số món ăn ngon, đặc sản nổi tiếng Kon Tum và địa chỉ cụ thể bạn nên tham khảo.', NULL),
(30, 'Lai Châu', 'Khi đến với Lai Châu bên cạnh những danh lam thắng cảnh nổi tiếng thì bạn còn được thưởng thức những món ăn thơm ngon hấp dẫn đậm chất núi rừng và hòa quyện cùng với hương vị rất riêng biệt sẽ đem lại cho bạn những trải nghiệm thú vị. Vùng núi Lai Châu hùng vĩ có rất nhiều món đặc sản ngon mà nghe có vẻ kì lạ nhưng rất tốt cho sức khỏe như lợn cắp nách, thịt khô gác bếp, rêu nướng, nộm rau dớn,… đã làm mê lòng bao thực khách gần xa du lịch miền đất tuyệt đẹp này.', NULL),
(31, 'Lâm Đồng', 'Đến với Lâm Đồng, du khách không những sẽ được khám phá những danh thắng đẹp miền cao nguyên trong tiết trời se lạnh mà còn bị mê hoặc bởi rất nhiều món ngon đặc sản. Lâm Đồng không chỉ có Đà Lạt, thác Dambri, những nông trường trà xanh bát ngát mà còn có nhiều món ăn ngon tại Lâm Đồng thơm ngon, hấp dẫn, làm say đắm lòng người. Đến đây, dù có đi đâu, làm gì thì đừng nên bỏ lỡ việc thưởng thức những món ăn dưới đây nhé!', NULL),
(32, 'Lạng Sơn', 'Lạng Sơn có nhiều đặc sản hấp dẫn, từ món mặn đến món ngọt, món chính đến món ăn vặt mà du khách đến đây nhất định nên dành thời gian thưởng thức.Nếu có dịp đến với Lạng Sơn, một tỉnh miền núi với những ngọn núi hùng vĩ, cảnh đẹp nên thơ du khách cũng đừng quên thưởng thức những món ăn đặc sản hay những đồ ăn vặt tiêu biểu cho nền ẩm thực phong phú nơi đây.', NULL),
(33, 'Lào Cai', 'Lào Cai là một khu du lịch nổi tiếng của miền Bắc, nơi có khung cảnh hoang sơ, trữ tình của rừng núi, với những thắng cảnh đẹp như trong mơ, khí hậu mát mẻ đó là những điều tuyệt vời khiến vùng đất này trở nên quyến rũ lạ thường. Không chỉ đến đây để khám phá, vãn cảnh du khách còn tìm đến những quán ăn ngon để thưởng thức hương vị ẩm thực nơi đây. Nếu đây là chuyến du lịch lần đầu của bạn, chắc chắn sẽ còn nhiều bỡ ngỡ hãy “note” danh sách địa điểm ăn uống dưới đây để khỏi “đi lạc” nhé.', NULL),
(34, 'Long An', 'Du khách có dịp đặt chân đến Long An không nên bỏ lỡ những món ăn đặc sản như canh chua cá chốt, cá lóc nướng trui, lẩu mắm, gạo Nàng thơm chợ Đào, lạp xưởng, rượu đế Gò Đen…Đến Long An ngoài việc thưởng ngoạn các phong cảnh thiên nhiên, khám phá các nét văn hóa độc đáo của người bản địa thì việc thưởng thức các món ăn ngon, mua quà về cho người thân là nhu cầu không thể thiếu của mỗi du khách.', NULL),
(35, 'Nam Định', 'Nam Định là vùng đất nằm ở phía Đông Nam vùng đồng bằng châu thổ sông Hồng, Nam Định đang bảo tồn và lưu giữ nhiều giá trị văn hóa truyền thống đặc sắc, trong đó có các phong tục, tín ngưỡng, lễ hội, làng nghề, di tích lịch sử văn hóa… Ngoài các giá trị di sản văn hóa ấy Nam Định còn có điều kiện phát triển du lịch sinh thái, đặc biệt là du lịch biển, bên cạnh đó nơi đây nổi tiếng với những món ăn phong phú. Vùng đất này luôn mời gọi, chào đón du khách gần xa về khám phá và trải nghiệm. ', NULL),
(36, 'Nghệ An', ' Nghệ An là mảnh đất non nước hữu tình đẹp như bức tranh thủy mặc. Vẻ đẹp kỳ thú pha lẫn chút nguyên sơ luôn làm say đắm bất cứ ai đã từng đi qua và dừng chân ghé lại nơi này. Không chỉ có thế, trên mảnh đất hồn hậu, địa linh nhân kiệt đã sản sinh ra nhiều đặc sản mà ai “lỡ” nếm thử một lần cũng vương vấn mãi không thôi. Các món ăn ngon ở Nghệ An mang chất riêng đặc biệt đến nỗi bất cứ ai có cơ hội được thưởng thức đều nhớ mãi không quên.', NULL),
(37, 'Ninh Bình', 'Ninh Bình nổi tiếng với nhiều địa danh du lịch nổi tiếng như khu du lịch Tràng An, Bái Đính, Tam Cốc- Bích Động, nhà thờ đá Phát Diệm… bên cạnh đó không thể không nhắc tới những món ăn ngon, những món ăn đặc sản nổi tiếng của Ninh Bình khi du lịch Ninh Bình được. Và bạn cần biết danh sách quán ăn ngon giá rẻ ở Ninh Bình- Ẩm thực Ninh Bình, và những địa chỉ ăn uống giá rẻ ở Ninh Bình cho bạn tha hồ chọn lựa, bởi vì không phải quán ăn nào cũng ngon và giá cả vừa túi tiền.', NULL),
(38, 'Ninh Thuận', 'Nắng gió đã ngấm vào từng thớ đất của Ninh Thuận để tạo nên những loại đặc sản bình dị nhưng cũng rất hấp dẫn của vùng đất này. Đến với Ninh Thuận bạn sẽ không chỉ được ngắm những bãi biển xinh đẹp mà bạn còn được thưởng thức những món ăn đặc sản, món ăn ngon ở Ninh Thuận không thể bỏ lỡ khi du lịch. Chính vẻ đẹp và sự phong phú của ẩm thực nơi đây đang dần làm nên thương hiệu cho du lịch Ninh Thuận. Những ai đã từng đi qua vùng đất này đều không thể bỏ lỡ những món ngon đặc sản nơi đây như nho, mực một nắng, tỏi, thịt cừu, thịt dông…', NULL),
(39, 'Phú Thọ', 'Phú Thọ không chỉ là nơi đất tổ của các vị vua Hùng mà còn là mảnh đất của nhiều đặc sản độc đáo khó có thể tìm thấy được ở những nơi khác trên cả nước. Phú Thọ không chỉ có di tích lịch sử, những con sông, ngọn núi hùng vĩ. Đến với mảnh đất mưa nắng thuận hòa này, nhất định bạn phải nếm các món đặc sản nơi đây. Có lẽ, sự hấp dẫn của những món ăn dân dã chính là sợi dây níu chân bao du khách, đi rồi vẫn muốn trở lại đầy luyến tiếc. Giờ hãy cùng khám phá những món ngon đặc sản Phú Thọ mà du khách không thể bỏ qua khi đến đây nhé!', NULL),
(40, 'Quảng Bình', 'Quảng Bình là vùng đất trù phú với cảnh sắc thiên nhiên phong phú, nhiều danh lam thắng cảnh được tạo hóa ban tặng nên hàng năm lại đón hàng triệu lượt khách tới tham quan.  Đặc sản Quảng Bình làm quà – nơi đây không chỉ được biết đến với những hang động hoang sơ, thác nước hùng vĩ mà còn khiến du khách “say lòng” bởi những món ăn đặc sản ngon miệng. Những món ăn đậm chất quê hương mộc mạc dưới đây sẽ mang đến cho bạn thêm nhiều trải nghiệm thú vị về đời sống văn hóa, tinh thần của người dân Quảng Bình. Vậy ăn ở đâu ngon khi đi du lịch Quảng Bình và địa chỉ các quán ăn đặc sản nổi tiếng ở Quảng Bình để bạn tới thưởng thức sau khi đi vui chơi là gì thì bạn hãy xem ngay danh sách dưới đây nhé!', NULL),
(41, 'Quảng Nam', 'Tỉnh Quảng Nam nằm tại khúc ruột miền Trung, nơi đây không chỉ nổi tiếng với các địa danh Tam Kỳ hay Hội An mà còn thu hút bởi các đặc sản cực kỳ phong phú chỉ có tại Quảng Nam. Nổi tiếng với nhiều cảnh đẹp, Quảng Nam thu hút du khách trong nước và quốc tế với ẩm thực dân dã, đậm đà đầy ấn tượng.Từ lâu, xứ Quảng đã nổi tiếng gần xa với văn hóa ẩm thực đặc sắc. Và nhắc tới đặc sản Quảng Nam không thể không kể tới mì Quảng, cao lầu, bánh tổ…', NULL),
(42, 'Quảng Ngãi', 'Ở đây, không chỉ chinh phục khách du lịch bằng cảnh sắc thiên nhiên đẹp đẽ mà còn đánh thức vị giác của chúng ta bằng những thức quà quê hương vô cùng giản đơn những không hề kém phần độc đáo và hấp dẫn. Những món ăn nơi đây làm cho ai đến đây cũng điều nhớ và hy vọng sẽ được quay lại một lần nữa.', NULL),
(43, 'Quảng Ninh', 'Văn hoá ẩm thực ở vùng biển Quảng Ninh có đặc điểm là cùng một món ăn nhưng lại có nét riêng biệt, mang đặc trưng của vùng mình. Ví dụ như vùng huyện đảo Vân Đồn, Cô Tô, Quảng Yên đều có mực ống, hà, tù hài... Khi đến với Quảng Ninh, một vùng đất giàu tính nhân văn mang đậm nét văn hoá Việt, bạn bè gần xa lại có dịp thưởng thức những món ăn dân gian khó quên, những món ăn ở Quảng Ninh, một phần nét ẩm thực của vùng biển Đông Bắc Việt Nam.', NULL),
(44, 'Quảng Trị', 'Quảng Trị, mảnh đất nơi dải đất miền Trung, thời tiết khắc nghiệt nắng lắm, mưa nhiều, nhưng lại có một nền văn hóa ẩm thực phong phú, đa dạng và vô cùng tinh tế. Để hiểu thêm về con người, văn hóa sống của vùng đất này, bạn đừng quên khám phá ẩm thực Quảng Trị qua các món ăn chứa đựng cả nhân sinh quan của con người nơi đâyQuảng Trị tuy không được mệnh danh là địa phương của tinh hoa ẩm thực, thế nhưng nơi đây cũng có nhiều món ăn trứ danh, đặc sắc, làm “mát lòng” du khách mỗi khi có dịp dừng chân. Những món ăn này được chắt chiu từ thời tiết khắc nghiệt nên mang hương vị riêng biệt, khó hòa lẫn với các vùng đất khác trên Tổ quốc.', NULL),
(45, 'Sóc Trăng', 'Từ xưa tới nay, đặc sản Sóc Trăng luôn là nét hấp dẫn riêng biệt hấp dẫn khách thập phương đến với mảnh đất miền Nam này. Ai đã một lần đặt chân tới đây, chắc chắn sẽ không quên được dư vị của những thức quà giản dị nhưng khó tìm này. Đến với vùng đất Sóc Trăng , bạn sẽ được thưởng thức những đặc sản món ngon nổi tiếng cả nước như vú sữa tím, bánh pía, khô trâu, mè láo, hủ tíu cà ri…', NULL),
(46, 'Sơn La', ' Sơn La là vùng đất sinh sống lâu đời của 12 dân tộc anh em, mỗi dân tộc đều có những bản sắc văn hóa riêng, độc đáo và tương đồng có lẽ vậy mà các món ăn ngon tại Sơn La mang những hương vị không trộn lẫn, có từ ngàn đời nay. Do có cộng đồng người Thái sinh sống đông nhất cả nước, ẩm thực Sơn La mang nhiều nét đặc trưng của dân tộc Thái. Hiện nay, một số món ăn của người Thái đã trở thành các sản phẩm để giới thiệu tại các nhà hàng, khách sạn, phục vụ khách du lịch đến Sơn La và được quảng bá ở một số tỉnh, thành phố trong cả nước.', NULL),
(47, 'Tây Ninh', 'Tây Ninh cách Sài Gòn khoảng 100 km về phía tây bắc, là điểm đến thú vị cho những chuyến du lịch ngắn ngày hay dã ngoại cùng bạn bè. Ngoài những địa điểm du lịch hấp dẫn, bạn đừng quên thưởng thức 10 món ăn dân dã nhưng vô cùng hấp dẫn dưới đây: Bánh canh Trảng Bàng, Bánh tráng phơi sương cuốn thịt, Món ăn chay Tây Ninh, Bò tơ Củ Chi,  Mắm chua Tây Ninh, Ốc xu núi Bà, Thằn lằn núi Bà Đen, Muối tôm, Nem bưởi, Bánh tráng me.', NULL),
(48, 'Thái Bình', 'Những món đặc sản Thái Bình không chỉ gây thương nhớ với những người con xa quê, mà ngay cả với những du khách dù chỉ được ăn một lần cũng sẽ nhớ mãi những hương vị thơm ngon ấy. Thái Bình nổi tiếng với bánh cáy, với canh cá Quỳnh Côi… Ngoài ra, nơi đây còn có nhiều món đặc sản thơm ngon, mang đậm đà hương vị mộc mạc mà tinh tế của vùng đất dân dã ấy.', NULL),
(49, 'Thái Nguyên', 'Thái Nguyên nằm ở phía Đông Bắc của Tổ quốc, là một trong những trung tâm văn hóa - kinh tế - chính trị trọng yếu của khu vực Đông Bắc cũng như vùng trung du miền núi phía Bắc. Thái Nguyên không chỉ là vùng đất được du khách biết đến với nhiều danh làm thắng cảnh và di tích lịch sử, mà còn là nơi có văn hóa ẩm thực vô cùng độc đáo với nhiều đặc sản mang đậm tinh hoa núi rừng.', NULL),
(50, 'Thanh Hóa', 'Sẽ thật thiếu sót nếu đến Thanh Hóa mà bạn lại bỏ qua cơ hội trải nghiệm những món ngon, ẩm thực xứ Thanh cũng là điểm nhấn không kém phần hấp dẫn so với những địa điểm du lịch hay danh lam thắng cảnh tại đây. Đến Thanh Hóa, ngoài việc ghé thăm Thành Nhà Hồ, suối Cá Thần Cẩm Lương, chèo thuyền trên sông Mã hay tắm biển Sầm Sơn thì du khách đừng quên thưởng thức những món ăn đặc sản tiêng có ở nơi đây.', NULL),
(51, 'Thừa Thiên Huế', 'Huế nổi tiếng với ẩm thực cung đình cao sang và mĩ vị, tuy nhiên các gánh hàng rong, các món ăn bình dân cũng có sức hấp dẫn khó cưỡng. Bánh canh, bánh nậm, bánh bèo hay bánh ướt... là những món ăn vặt hấp dẫn mà du khách mỗi khi đến Huế thường muốn thưởng thức ít nhất một lần để hiểu hơn về ẩm thực miền cố đô.', NULL),
(52, 'Tiền Giang', 'Tây có mặt ở vùng quê Tiền Giang Mắm còng lột, mắm tôm chà, cháo cá lóc rau đắng, vú sữa, sam biển…là những món ngon đặc trưng của người miền . Sở thích ăn vặt của người Tiền Giang không những chỉ có vào những ngày hè oi ả mà người Tiền Giang ăn vặt quanh năm. Có lẽ vì thế mà các quán ăn vặt trên mảnh đất này, cũng cứ thế mà nườm nượp khách vào, khách ra.', NULL),
(53, 'Trà Vinh', 'Trà Vinh – nơi sinh ra và lớn lên của vô số người con dân tộc Khơ-me, hiền lành, chất phác, yêu cái đẹp và họ tạo ra một nền ẩm thực đậm chất dân gian, gần gũi với thiên nhiên. Không chỉ là vương quốc của dừa sáp, Trà Vinh còn nổi tiếng nhờ món bún suông, nước mắm rươi và bánh tét cốm dẹp và nhiều món ăn không thể cưỡng lại.', NULL),
(54, 'Tuyên Quang', 'Tuyên Quang là một vùng đất nổi tiếng với nhiều đặc sản vùng cao, cùng nhiều di tích lịch sử văn hóa mang dấu ấn đặc trưng của người Việt. Bên cạnh đó đồ ăn vặt Tuyên quang cũng là điều khiến teen Tuyên Quang tự hào và nhớ day dứt mỗi khi xa nhà. Những ai từng đi qua “miền gái đẹp” nổi tiếng này đều khó có thể quên được những món ăn ngon trở thành đặc sản nơi đây như: Bánh nếp nhân trứng kiến, thịt lợn đen, lạp xưởng, gỏi cá bỗng, rượu ngô, mắm cá ruộng...', NULL),
(55, 'Vĩnh Long', 'Vĩnh Long không chỉ có những điểm tham quan đẹp và nổi tiếng cùng với đó là vô số các món ăn ngon. Đi du lịch ngoài mục đích được nghỉ ngơi, thư giãn, ngắm nhìn các cảnh đẹp…thì các du khách còn muốn thưởng thức các món ăn đặc sản tại nơi những nơi mình đặt chân đến. Nếu các bạn đang có dự định du lịch tại Vĩnh Long mà chưa biết thưởng thức các món đặc sản của vùng đất này ở đâu thì hãy bỏ túi ngay top 10 món ăn sau: Cá tai tượng chiên xù, Khoai lang mắm sống cuốn lá cách, Cá lóc nướng trui, Bánh tráng nem cù lao, Cá út nấu canh chua, Các món ngon lạ từ nấm mối, Cá cóc kho nước dừa, cá cóc nấu canh chua.', NULL),
(56, 'Vĩnh Phúc', 'Vĩnh Phúc không chỉ giữ chân du khách bởi vẻ đẹp của một Tam Đảo mù sương, nét uy nghiêm của Thiền viện trúc lâm Tây Thiên hay nét đẹp thơ mộng của hồ Đại Lải,… mà còn bởi những món đặc sản ngon nức tiếng của nơi đây.Sau đây là một số món ăn làm nên tên tuổi của tỉnh Vĩnh Phúc: Bánh ngõa Lũng Ngoại, Bánh trùng mật mía Vĩnh Tường, Bánh gio Tây Đình, Chè kho Tứ Yên, Bò tái kiến đốt, Cá thính Lập Thạch, Tép Dầu Đầm Vạc, Dứa Tam Dương, Su su Tam Đảo, Rượu dừa Tiên Tửu Ngọc Hoa,...', NULL),
(57, 'Yên Bái', 'Là một tỉnh miền núi Tây Bắc, với phong cảnh thiên nhiên đa dạng và nhiều đỉa điểm tham quan hấp dẫn như hang Thẩm Lé, hồ Thác Bà, du lịch sinh thái suối Giàng, cánh đồng Mường Lò và đặc biệt là ruộng bậc thang Mù Cang Chải… Tỉnh Yên Bái có nhiều dân tộc thiểu số và mỗi dân tộc mang đậm một bản sắc văn hoá riêng, là điểm đến tuyệt vời cho du khách.', NULL),
(58, 'Phú Yên', 'Nói đến Phú Yên ta không thể quên Gành Đá Đĩa, Đầm Ô Loan, Tháp Nhạn, Vũng Rô nơi huyền thoại về câu chuyện con tàu 0 số, và còn nhiều địa danh nổi tiếng khác. Lạc vào mảnh đất Phú Yên \"hoa vàng cỏ xanh\" đừng chỉ đắm chìm với cảnh đẹp mà bỏ quên rất nhiều món ăn ngon đặc sản nơi đây nhé. Món ăn ở Phú Yên có sự giao thoa giữa các vùng miền, tạo nên nhiều món ngon , lạ, và độc đáo.', NULL),
(59, 'Cần Thơ', 'Đi Cần Thơ nếm tròn vị các món bánh cống, cá lóc nướng trui, bún mắm,… có một cái gì đó làm người ta luôn cảm thấy quyến luyến. Du lịch Cần Thơ gạo trắng nước trong cùng với văn hóa ẩm thực phong phú đa dạng luôn biết cách khiến du khách phải nao lòng. Thứ nhất, dù đi một mình, du khách đi Cần Thơ cũng không bao giờ cảm thấy lạc lõng vì chỉ cần cởi mở một chút, lân la hỏi chuyện người bản xứ là chắc chắn nhận về cả một kho những câu chuyện thú vị không để đâu cho hết. Thứ hai, đi thuyền trên sông nước mênh mông khi đi Cần Thơ chưa bao giờ nhàm chán, ngay cả người ở những nước tận đẩu tận đâu cũng tìm đến miền Tây để chèo thuyền cơ mà. Và thứ ba, quan trọng nhất, đồ ăn cực rẻ và cực ngon.', NULL),
(60, 'Đà Nẵng', 'Không phải ngẫu nhiên mà Đà Nẵng được mệnh danh là thành phố đáng sống nhất Việt Nam. Vùng đất này chinh phục trái tim của biết bao du khách đến đây nhờ phong cảnh tuyệt đẹp, khí hậu trong lành, nhiều địa điểm vui chơi thú vị mà còn có đến hàng trăm món ăn ngon người dân thân thiện và tất nhiên là không thể thiếu những món ăn đặc sản chỉ Đà Nẵng mới có. Quán ăn ngon níu chân du khách, nhất là các bạn trẻ yêu thích khám phá và thưởng thức những món ăn ngon.', NULL),
(61, 'Hải Phòng', 'Thành Phố Hải Phòng với nhiều món ăn ngon đặc sản khiến du khách nhớ thương chắc chắn sẽ là một điểm đến lý tưởng dành cho những con người có tâm hồn ăn uống phong phú, thích khám phá món ăn mới lạ. Vậy sau đây là một vài món ăn cho bạn có thể thưởng thức khi đến Hải Phòng: Nem Rán Trung Kiên, Chè Thái & Bánh Mì Cay – Đinh Tiên Hoàng, Bánh Đúc Tàu – Cát Dài, Ốc Thủy Dương, Bánh đa cua đồng 48 Lạch Tray, Lẩu cua đồng đường Văn Cao, Bún Tôm Hợi Hợi...', NULL),
(62, 'Hà Nội', 'Muốn thưởng thức những quán ăn ngon của Hà Nội, đôi khi bạn phải chui vào từng ngóc ngách, từng con phố nhỏ của Hà Nội. Dưới đây là 23 địa chỉ những quán ngon nức tiếng nhất định phải thử ở Hà Nội một lần trong đời: Mỳ vằn thắn Bình Tây, Bánh mỳ sốt vang Trâm, Bánh tráng trộn, Miến trộn mực, Bánh đa trộn Dốc Thọ Lão, Phở chiên giòn, Miến lươn Đông Thịnh, Miến xào cua, Bún thang bún bung, Bún ốc cô Huệ, Bún măng ngan, Bún mọc dọc mùng, Cháo sườn Hàng Bồ, Xôi rán Hàng Điếu, Bánh đúc nóng,...', '\"21\"-\"105.75\"'),
(63, 'TP HCM', 'Một trong những niềm vui còn sót lại của dân Sài Gòn có lẽ là lúc nào cũng được ăn phủ phê những món ngon lành. Cứ ra đường phố Sài Gòn, bạn sẽ bắt gặp hằng hà sa số hàng quán bán đồ ăn. Món chính ngon, mà món vặt cũng ngon. Nếu là lần đầu tiên bạn đặt chân đến Sài Gòn, bạn nhất định phải thử qua những món trong danh sách dưới đây: Bánh tráng trộn, Gỏi khô bò, Phá lấu, Vú dê nướng, Sủi cảo, Kem nhãn.', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `recept`
--

CREATE TABLE `recept` (
  `id` int(11) NOT NULL,
  `restaurant` int(11) NOT NULL,
  `food` int(11) NOT NULL,
  `customer` int(11) NOT NULL,
  `quantum` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `receptTime` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `restaurant`
--

CREATE TABLE `restaurant` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `image` text NOT NULL,
  `latLong` text,
  `province` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `restaurant`
--

INSERT INTO `restaurant` (`id`, `name`, `image`, `latLong`, `province`) VALUES
(1, 'Nhà hàng Đông Á', 'https://images.foody.vn/res/g20/193524/prof/s576x330/foody-mobile-1784-jpg-380-635851679008099885.jpg', '21.054386<>105.73511', 62),
(2, 'Nhà hàng Đông Á', 'https://www.cukcuk.vn/wp-content/uploads/2019/02/nha_hang_1.jpg', '21.03715455<>105.80479798', 62),
(3, 'Nhà hàng Tây Phương', 'http://phadocongtrinhuytin.com/upload/images/thu-mua-thanh-ly-nha-hang-4.JPG', '21.02854145<>105.84073828', 62),
(4, 'Nhà hàng Á Đông', 'https://d2e5ushqwiltxm.cloudfront.net/wp-content/uploads/sites/82/2016/12/06032003/2.-Restaurantsandbars-foodexchangerestaurant.jpg', '21.01129462<>105.85221443', 62);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `comment_fk0` (`customer`),
  ADD KEY `comment_fk1` (`food`),
  ADD KEY `comment_fk2` (`restaurant`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `food`
--
ALTER TABLE `food`
  ADD PRIMARY KEY (`id`),
  ADD KEY `food_fk0` (`category`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD KEY `menu_fk0` (`food`),
  ADD KEY `menu_fk1` (`restaurant`);

--
-- Indexes for table `price`
--
ALTER TABLE `price`
  ADD PRIMARY KEY (`id`),
  ADD KEY `price_fk0` (`food`),
  ADD KEY `price_fk1` (`restaurant`);

--
-- Indexes for table `province`
--
ALTER TABLE `province`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `recept`
--
ALTER TABLE `recept`
  ADD PRIMARY KEY (`id`),
  ADD KEY `recept_fk0` (`restaurant`),
  ADD KEY `recept_fk1` (`food`),
  ADD KEY `recept_fk2` (`customer`),
  ADD KEY `recept_fk3` (`price`);

--
-- Indexes for table `restaurant`
--
ALTER TABLE `restaurant`
  ADD PRIMARY KEY (`id`),
  ADD KEY `restaurant_fk0` (`province`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `food`
--
ALTER TABLE `food`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `price`
--
ALTER TABLE `price`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `province`
--
ALTER TABLE `province`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT for table `recept`
--
ALTER TABLE `recept`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `restaurant`
--
ALTER TABLE `restaurant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_fk0` FOREIGN KEY (`customer`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `comment_fk1` FOREIGN KEY (`food`) REFERENCES `food` (`id`),
  ADD CONSTRAINT `comment_fk2` FOREIGN KEY (`restaurant`) REFERENCES `restaurant` (`id`);

--
-- Constraints for table `food`
--
ALTER TABLE `food`
  ADD CONSTRAINT `food_fk0` FOREIGN KEY (`category`) REFERENCES `category` (`id`);

--
-- Constraints for table `menu`
--
ALTER TABLE `menu`
  ADD CONSTRAINT `menu_fk0` FOREIGN KEY (`food`) REFERENCES `food` (`id`),
  ADD CONSTRAINT `menu_fk1` FOREIGN KEY (`restaurant`) REFERENCES `restaurant` (`id`);

--
-- Constraints for table `price`
--
ALTER TABLE `price`
  ADD CONSTRAINT `price_fk0` FOREIGN KEY (`food`) REFERENCES `food` (`id`),
  ADD CONSTRAINT `price_fk1` FOREIGN KEY (`restaurant`) REFERENCES `restaurant` (`id`);

--
-- Constraints for table `recept`
--
ALTER TABLE `recept`
  ADD CONSTRAINT `recept_fk0` FOREIGN KEY (`restaurant`) REFERENCES `restaurant` (`id`),
  ADD CONSTRAINT `recept_fk1` FOREIGN KEY (`food`) REFERENCES `food` (`id`),
  ADD CONSTRAINT `recept_fk2` FOREIGN KEY (`customer`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `recept_fk3` FOREIGN KEY (`price`) REFERENCES `price` (`id`);

--
-- Constraints for table `restaurant`
--
ALTER TABLE `restaurant`
  ADD CONSTRAINT `restaurant_fk0` FOREIGN KEY (`province`) REFERENCES `province` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
