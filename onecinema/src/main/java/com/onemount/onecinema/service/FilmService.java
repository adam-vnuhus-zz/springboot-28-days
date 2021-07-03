package com.onemount.onecinema.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.onemount.onecinema.model.Film;
import com.onemount.onecinema.model.Genre;
import com.onemount.onecinema.model.Language;
import com.onemount.onecinema.model.Rating;
import com.onemount.onecinema.repository.FilmRepository;
import com.onemount.onecinema.repository.GenreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmService {
  private static final String DD_MM_YYYY = "dd/MM/yyyy";

  @PersistenceContext
  private EntityManager em;
  @Autowired
  private GenreRepository genreRepo;
  @Autowired
  private FilmRepository filmRepo;

  @Transactional
  public void generateSampleFilms() throws ParseException {
    Genre hanhDong = genreRepo.findByName("Hành động");
    Genre kinhDi = genreRepo.findByName("Kinh dị");
    Genre hai = genreRepo.findByName("Hài");
    Genre tinhCam = genreRepo.findByName("Tình cảm");
    Genre hoiHop = genreRepo.findByName("Hồi hộp");
    Genre tamLy = genreRepo.findByName("Tâm lý");
    Genre giaDinh = genreRepo.findByName("Gia đình");
    Genre thanThoai = genreRepo.findByName("Thần thoại");
    Genre khoaHocVienTuong = genreRepo.findByName("Khoa học viễn tưởng");
    Genre phieuLuu = genreRepo.findByName("Phiêu lưu");

    Film banTayDietQuy = Film.builder().title("BÀN TAY DIỆT QUỶ").description(
        "Sau khi bản thân bỗng nhiên sở hữu “Bàn tay diệt quỷ”, võ sĩ MMA Yong Hoo (Park Seo Joon thủ vai) đã dấn thân vào hành trình trừ tà, trục quỷ đối đầu với Giám Mục Bóng Tối (Woo Do Hwan) – tên quỷ Satan đột lốt người. Từ đó sự thật về cái chết của cha Yong Hoo cũng dần được hé lộ cũng như nguyên nhân anh trở thành “người được chọn”.")
        .director("Kim Joo Hwan").actors("Park Seo Joon, Ahn Sung Ki, Woo Do Hwan, Choi Woo Sik…")
        .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("09/04/2021")).language(Language.KR).hasVietSubTitle(true)
        .lengthInMinutes(128).rating(Rating.C18)
        .thumbnail("https://media.lottecinemavn.com/Media/MovieFile/MovieImg/202103/10714_103_100002.jpg").build();
    banTayDietQuy.addGenre(hanhDong);
    banTayDietQuy.addGenre(kinhDi);

    Film palmSpring = Film.builder().title("BỐ GIÀ").description(
        "Phim sẽ xoay quanh lối sống thường nhật của một xóm lao động nghèo, ở đó có bộ tứ anh em Giàu - Sang - Phú - Quý với Ba Sang sẽ là nhân vật chính, hay lo chuyện bao đồng nhưng vô cùng thương con cái. Câu chuyện phim tập trung về hai cha con Ba Sang (Trấn Thành) và Quắn (Tuấn Trần). Dù yêu thương nhau nhưng khoảng cách thế hệ đã đem đến những bất đồng lớn giữa hai cha con. Liệu cả hai có thể cho nhau cơ hội thấu hiểu đối phương, thu hẹp khoảng cách và tạo nên hạnh phúc từ sự khác biệt?")
        .director("Vũ Ngọc Đãng & Trấn Thành")
        .actors(
            "Trấn Thành, Tuấn Trần, Ngân Chi, NSND Ngọc Giàu, Lê Giang, Lan Phương, Hoàng Mèo, La Thành, Quốc Khánh, Lê Trang, A Quay, Bảo Phúc,…")
        .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("12/03/2021")).language(Language.VN)
        .hasVietSubTitle(false).lengthInMinutes(128).rating(Rating.C13)
        .thumbnail("https://media.lottecinemavn.com/Media/MovieFile/MovieImg/202102/10656_103_100004.jpg").build();
    palmSpring.addGenre(hai);
    palmSpring.addGenre(giaDinh);

    Film trumCuoiSieuDang = Film.builder().title("TRÙM CUỐI SIÊU ĐẲNG").description(
        "Mắc kẹt trong một vòng lặp thời gian ngay đúng ngày anh ta bị giết chết, một cựu đặc vụ Roy Pulver (Frank Gillo) đã phát hiện ra manh mới về một dự án bí mật của chính phủ có thể giải đáp bí ẩn đằng sau cái chết vô thời hạn của anh ta. Roy buộc lòng phải chạy đua với thời gian và truy bắt tên Colonel Ventor (Mel Gibson), đầu sỏ của dự án chính phủ này. Trong lúc đó, anh phải thoát khỏi cuộc vây bắt của những tên sát thủ tàn ác quyết tâm ngăn cản Roy tìm ra được sự thật. Liệu Roy Pulver có thể thoát khỏi vòng lặp này và cứu lấy gia đình đồng thời sống lại một lần nữa vào ngày mai?")
        .director("Joe Carnahan")
        .actors("Frank Grillo, Mel Gibson, Naomi Watts, Dương Tử Quỳnh, Annabelle Wallis, Ken Jeong, Will Sasso, ….")
        .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("23/04/2021")).language(Language.EN).hasVietSubTitle(true)
        .lengthInMinutes(101).rating(Rating.C18)
        .thumbnail("https://media.lottecinemavn.com/Media/MovieFile/MovieImg/202104/10729_103_100001.jpg").build();
    trumCuoiSieuDang.addGenre(hanhDong);
    trumCuoiSieuDang.addGenre(hoiHop);

    Film cucNoHoaCucCung = Film.builder().title("GODZILLA VS. KONG").description(
        "Khi hai kẻ thù truyền kiếp gặp nhau trong một trận chiến ngoạn mục, số phận của cả thế giới vẫn còn bị bỏ ngỏ… Bị đưa khỏi Đảo Đầu Lâu, Kong cùng Jia, một cô bé mồ côi có mối liên kết mạnh mẽ với mình và đội bảo vệ đặc biệt hướng về mái nhà mới. Bất ngờ, nhóm đụng độ phải Godzilla hùng mạnh, tạo ra một làn sóng hủy diệt trên toàn cầu. Thực chất, cuộc chiến giữa hai kẻ khổng lồ dưới sự thao túng của các thế lực vô hình mới chỉ là điểm khởi đầu để khám phá những bí ẩn nằm sâu trong tâm Trái đất.")
        .director("Adam Wingard")
        .actors("Millie Bobby Brown, Alexander Skarsgård, Rebecca Hall, Eiza González, Kyle Chandler")
        .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("26/03/2021")).language(Language.EN).hasVietSubTitle(true)
        .lengthInMinutes(113).rating(Rating.C13)
        .thumbnail("https://media.lottecinemavn.com/Media/MovieFile/MovieImg/202103/10708_103_100002.jpg").build();
    cucNoHoaCucCung.addGenre(hanhDong);
    cucNoHoaCucCung.addGenre(thanThoai);

    Film banNangHoangDai = Film.builder().title("BẢN NĂNG HOANG DẠI").description(
        "Trong một tương lai không xa khi nhân loại đang trên bờ vực diệt chủng, một nhóm thanh thiếu niên được nuôi dưỡng để phục vụ cho mục đích khai thác trí tuệ và chấp nhận sai khiến. Họ bắt đầu một chuyến hành trình thám hiểm khai phá một hành tinh xa xôi khác. Nhưng khi họ phát hiện ra bí mật được che giấu đằng sau nhiệm vụ cao cả này, tất cả bắt đầu kháng cự lại việc huấn luyện này và bắt đầu để bản năng nguyên thuỷ lấn chiếm cảm xúc. Khi vận mệnh của cả phi đoàn rơi vào hỗn loạn, họ bị chôn vùi trong sợ hãi, ham muốn và cơn khao khát quyền lực.")
        .director("Neil Burger")
        .actors(
            "Tye Sheridan, Lily-Rose Depp, Fionn Whitehead, Chanté Adams, Isaac Hempstead Wright, Viveik Kalra, Archie Madekwe, Quintessa Swindell, Madison Hu, and Colin Farrell")
        .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("09/04/2021")).language(Language.EN).hasVietSubTitle(true)
        .lengthInMinutes(108).rating(Rating.C16)
        .thumbnail(
            "https://www.cgv.vn/media/catalog/product/cache/1/image/1800x/71252117777b696995f01934522c402d/p/o/poster_bnhd_voyagers-cgv_1__1.jpg")
        .build();
    banNangHoangDai.addGenre(hoiHop);
    banNangHoangDai.addGenre(khoaHocVienTuong);

    Film goaPhuDen = Film.builder().title("GÓA PHỤ ĐEN").description(
        "Phần phim riêng của Natasha Romanoff bất ngờ tung teaser chính thức với những cảnh hành động mãn nhãn, mang đậm chất điệp viên. Bên cạnh đó, phần phim này cũng sẽ hé lộ quá khứ đen tối và quá trình biến cô trở thành một Black Widow.")
        .director("Cate Shortland")
        .actors(
            "Scarlett Johansson, Florence Pugh, Robert Downey Jr...")
        .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("09/07/2021")).language(Language.EN).hasVietSubTitle(true)
        .lengthInMinutes(100).rating(Rating.C16)
        .thumbnail(
            "https://media.lottecinemavn.com/Media/MovieFile/MovieImg/202003/10444_103_100002.jpg")
        .build();
    goaPhuDen.addGenre(hanhDong);
    goaPhuDen.addGenre(phieuLuu);

    Film phim1990 = Film.builder().title("1990").description(
        "Bộ phim ‘1990’ là cú bắt tay giữa ba ngọc nữ tuổi Ngọ của điện ảnh Việt: Diễm My - Ninh Dương Lan Ngọc và Nhã Phương. ‘1990’ thuộc thể loại Hài - Tình cảm, có nội dung xoay quanh một hội bạn thân gồm ba cô gái với ba cá tính khác nhau. Khi ngưỡng tuổi “30 chênh vênh” ập đến với cả ba vào cùng một thời điểm, hàng loạt những vấn đề về hôn nhân, tình yêu, sự nghiệp,... lần lượt xuất hiện, buộc họ phải giúp đỡ nhau vượt qua cột mốc đầy sóng gió này. ‘1990’ do đạo diễn Nhất Trung cầm trịch, dự kiến ra mắt vào ngày 21.04.2021.")
        .director("Nhất Trung").actors("Diễm My, Ninh Dương Lan Ngọc, Nhã Phương...")
        .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("11/08/2021")).language(Language.VN).hasVietSubTitle(false)
        .lengthInMinutes(120).rating(Rating.C16)
        .thumbnail("https://www.cgv.vn/media/catalog/product/cache/1/image/c5f0a1eff4c394a251036189ccddaacd/1/9/1990_-_payoff_poster_-_kc_21042021_1_.jpg").build();
    phim1990.addGenre(tinhCam);
    phim1990.addGenre(hai);

    Film bayNgotNgao = Film.builder().title("BẪY NGỌT NGÀO").description(
        "Sau một thời gian dài không gặp, cuộc sống của mỗi thành viên trong hội ế đều có nhiều thay đổi. Camy là người duy nhất “thoát ế” với cuộc sống đáng mơ ước bên người chồng tài hoa Đăng Minh. Quỳnh Lam đã là một nhà thiết kế nổi tiếng, Linh Đan là một luật sư thành đạt, còn Ken trở thành ông chủ phòng gym. Cả nhóm quyết định hội ngộ nhân dịp kỷ niệm 3 năm ngày cưới của Camy. Từ đây, những góc khuất trong cuộc sống riêng của từng người dần được hé lộ. Hội ế sẽ làm gì khi phát hiện ra những bí mật của mỗi thành viên lại là nguyên nhân khiến tình bạn của họ đối mặt với sóng gió.")
        .director("Đinh Hà Uyên Thư").actors("Bảo Anh, Minh Hằng, Diệu Nhi, Thuận Nguyễn, Quốc Trường.")
        .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("03/06/2022")).language(Language.VN).hasVietSubTitle(false)
        .lengthInMinutes(150).rating(Rating.C16)
        .thumbnail("https://www.cgv.vn/media/catalog/product/cache/1/image/c5f0a1eff4c394a251036189ccddaacd/m/a/main-poster_bnn_copy_1_.jpg").build();
    bayNgotNgao.addGenre(tamLy);

    em.persist(banTayDietQuy);
    em.persist(palmSpring);
    em.persist(trumCuoiSieuDang);
    em.persist(cucNoHoaCucCung);
    em.persist(banNangHoangDai);
    em.persist(goaPhuDen);
    em.persist(phim1990);
    em.persist(bayNgotNgao);
  }

  public List<Film> findAll() {
    return filmRepo.findAll();
  }
}
