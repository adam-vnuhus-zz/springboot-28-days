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

  @Autowired private  GenreRepository genreRepo;
  @Autowired private  FilmRepository filmRepo;

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

    Film banTayDietQuy = Film.builder()
    .title("BÀN TAY DIỆT QUỶ")
    .description("Sau khi bản thân bỗng nhiên sở hữu “Bàn tay diệt quỷ”, võ sĩ MMA Yong Hoo (Park Seo Joon thủ vai) đã dấn thân vào hành trình trừ tà, trục quỷ đối đầu với Giám Mục Bóng Tối (Woo Do Hwan) – tên quỷ Satan đột lốt người. Từ đó sự thật về cái chết của cha Yong Hoo cũng dần được hé lộ cũng như nguyên nhân anh trở thành “người được chọn”.")
    .director("Kim Joo Hwan")
    .actors("Park Seo Joon, Ahn Sung Ki, Woo Do Hwan, Choi Woo Sik…")
    .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("09/04/2021"))
    .language(Language.KR)
    .hasVietSubTitle(true)
    .lengthInMinutes(128)
    .rating(Rating.C18)
    .thumbnail("https://media.lottecinemavn.com/Media/MovieFile/MovieImg/202103/10714_103_100002.jpg")
    .build();

    banTayDietQuy.addGenre(hanhDong);
    banTayDietQuy.addGenre(kinhDi);

    Film palmSpring = Film.builder()
    .title("BỐ GIÀ")
    .description("Phim sẽ xoay quanh lối sống thường nhật của một xóm lao động nghèo, ở đó có bộ tứ anh em Giàu - Sang - Phú - Quý với Ba Sang sẽ là nhân vật chính, hay lo chuyện bao đồng nhưng vô cùng thương con cái. Câu chuyện phim tập trung về hai cha con Ba Sang (Trấn Thành) và Quắn (Tuấn Trần). Dù yêu thương nhau nhưng khoảng cách thế hệ đã đem đến những bất đồng lớn giữa hai cha con. Liệu cả hai có thể cho nhau cơ hội thấu hiểu đối phương, thu hẹp khoảng cách và tạo nên hạnh phúc từ sự khác biệt?")
    .director("Vũ Ngọc Đãng & Trấn Thành")
    .actors("Trấn Thành, Tuấn Trần, Ngân Chi, NSND Ngọc Giàu, Lê Giang, Lan Phương, Hoàng Mèo, La Thành, Quốc Khánh, Lê Trang, A Quay, Bảo Phúc,…")
    .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("12/03/2021"))
    .language(Language.VN)
    .hasVietSubTitle(false)
    .lengthInMinutes(128)
    .rating(Rating.C13)
    .thumbnail("https://media.lottecinemavn.com/Media/MovieFile/MovieImg/202102/10656_103_100004.jpg")
    .build();
    palmSpring.addGenre(hai);
    palmSpring.addGenre(giaDinh);


    Film trumCuoiSieuDang = Film.builder()
    .title("TRÙM CUỐI SIÊU ĐẲNG")
    .description("Mắc kẹt trong một vòng lặp thời gian ngay đúng ngày anh ta bị giết chết, một cựu đặc vụ Roy Pulver (Frank Gillo) đã phát hiện ra manh mới về một dự án bí mật của chính phủ có thể giải đáp bí ẩn đằng sau cái chết vô thời hạn của anh ta. Roy buộc lòng phải chạy đua với thời gian và truy bắt tên Colonel Ventor (Mel Gibson), đầu sỏ của dự án chính phủ này. Trong lúc đó, anh phải thoát khỏi cuộc vây bắt của những tên sát thủ tàn ác quyết tâm ngăn cản Roy tìm ra được sự thật. Liệu Roy Pulver có thể thoát khỏi vòng lặp này và cứu lấy gia đình đồng thời sống lại một lần nữa vào ngày mai?")
    .director("Joe Carnahan")
    .actors("Frank Grillo, Mel Gibson, Naomi Watts, Dương Tử Quỳnh, Annabelle Wallis, Ken Jeong, Will Sasso, ….")
    .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("23/04/2021"))
    .language(Language.EN)
    .hasVietSubTitle(true)
    .lengthInMinutes(101)
    .rating(Rating.C18)
    .thumbnail("https://media.lottecinemavn.com/Media/MovieFile/MovieImg/202104/10729_103_100001.jpg")
    .build();
    trumCuoiSieuDang.addGenre(hanhDong);
    trumCuoiSieuDang.addGenre(hoiHop);

    Film cucNoHoaCucCung = Film.builder()
    .title("GODZILLA VS. KONG")
    .description("Khi hai kẻ thù truyền kiếp gặp nhau trong một trận chiến ngoạn mục, số phận của cả thế giới vẫn còn bị bỏ ngỏ… Bị đưa khỏi Đảo Đầu Lâu, Kong cùng Jia, một cô bé mồ côi có mối liên kết mạnh mẽ với mình và đội bảo vệ đặc biệt hướng về mái nhà mới. Bất ngờ, nhóm đụng độ phải Godzilla hùng mạnh, tạo ra một làn sóng hủy diệt trên toàn cầu. Thực chất, cuộc chiến giữa hai kẻ khổng lồ dưới sự thao túng của các thế lực vô hình mới chỉ là điểm khởi đầu để khám phá những bí ẩn nằm sâu trong tâm Trái đất.")
    .director("Adam Wingard")
    .actors("Millie Bobby Brown, Alexander Skarsgård, Rebecca Hall, Eiza González, Kyle Chandler")
    .beginShowDate(new SimpleDateFormat(DD_MM_YYYY).parse("26/03/2021"))
    .language(Language.EN)
    .hasVietSubTitle(true)
    .lengthInMinutes(113)
    .rating(Rating.C13)
    .thumbnail("https://media.lottecinemavn.com/Media/MovieFile/MovieImg/202103/10708_103_100002.jpg")
    .build();

    cucNoHoaCucCung.addGenre(hanhDong);
    cucNoHoaCucCung.addGenre(thanThoai);

    em.persist(banTayDietQuy);
    em.persist(palmSpring);
    em.persist(trumCuoiSieuDang);
    em.persist(cucNoHoaCucCung);
  }

  public List<Film> findAll() {
    return filmRepo.findAll();
  }
}
