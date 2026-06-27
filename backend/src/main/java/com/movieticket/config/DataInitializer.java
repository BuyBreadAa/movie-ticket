package com.movieticket.config;

import com.movieticket.entity.*;
import com.movieticket.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepo userRepo;
    private final MovieRepo movieRepo;
    private final CinemaRepo cinemaRepo;
    private final HallRepo hallRepo;
    private final ShowRepo showRepo;
    private final ReviewRepo reviewRepo;
    private final PasswordEncoder encoder;

    public DataInitializer(UserRepo userRepo, MovieRepo movieRepo, CinemaRepo cinemaRepo,
                           HallRepo hallRepo, ShowRepo showRepo, ReviewRepo reviewRepo,
                           PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.movieRepo = movieRepo;
        this.cinemaRepo = cinemaRepo;
        this.hallRepo = hallRepo;
        this.showRepo = showRepo;
        this.reviewRepo = reviewRepo;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        if (userRepo.count() > 0) return; // 已有数据则跳过

        // 用户 (密码: 12345678)
        User admin = new User("13800138000", "admin@movie.com", encoder.encode("12345678"), "管理员");
        admin.setRole("admin"); admin.setLevel("超级VIP"); admin.setPoints(5000);
        userRepo.save(admin);

        User user = new User("13900139000", "user@movie.com", encoder.encode("12345678"), "影迷小明");
        user.setLevel("VIP会员"); user.setPoints(1280);
        userRepo.save(user);

        // 影片
        Movie m1 = movie("星际穿越", "克里斯托弗·诺兰", "马修·麦康纳, 安妮·海瑟薇", "科幻", "美国", 2014, "一组宇航员穿越虫洞，为人类寻找新家园。", 9.4);
        Movie m2 = movie("肖申克的救赎", "弗兰克·德拉邦特", "蒂姆·罗宾斯, 摩根·弗里曼", "剧情", "美国", 1994, "银行家安迪被误判谋杀，在监狱中坚守希望二十年。", 9.7);
        Movie m3 = movie("千与千寻", "宫崎骏", "柊瑠美, 入野自由", "动画", "日本", 2001, "女孩千寻误入神灵世界，为救父母而工作。", 9.4);
        Movie m4 = movie("盗梦空间", "克里斯托弗·诺兰", "莱昂纳多·迪卡普里奥, 约瑟夫·高登-莱维特", "科幻", "美国", 2010, "一个从梦中窃取秘密的团队，接受植入思想的任务。", 9.3);
        Movie m5 = movie("泰坦尼克号", "詹姆斯·卡梅隆", "莱昂纳多·迪卡普里奥, 凯特·温丝莱特", "爱情", "美国", 1997, "跨越阶级的爱情，在注定沉没的巨轮上绽放。", 9.4);
        Movie m6 = movie("楚门的世界", "彼得·威尔", "金·凯瑞, 劳拉·琳妮", "剧情", "美国", 1998, "楚门的整个人生都是一场真人秀，直到他开始怀疑一切。", 9.3);
        Movie m7 = movie("放牛班的春天", "克里斯托夫·巴拉蒂", "热拉尔·朱尼奥, 让-巴蒂斯特·莫尼耶", "音乐", "法国", 2004, "一位音乐教师用合唱改变了一群问题少年的人生。", 9.3);
        Movie m8 = movie("疯狂动物城", "拜伦·霍华德", "金妮弗·古德温, 杰森·贝特曼", "动画", "美国", 2016, "兔子朱迪来到动物城实现警察梦想，与狐狸尼克搭档破案。", 9.2);

        // 影院
        Cinema c1 = cinema("万达影城（朝阳店）", "朝阳区建国路93号万达广场B1", "010-88886666", "IMAX, 杜比全景声, 4K激光", "北京", "朝阳区");
        Cinema c2 = cinema("CGV影城（三里屯店）", "朝阳区工体北路甲2号", "010-66668888", "IMAX, ScreenX, 4D", "北京", "朝阳区");
        Cinema c3 = cinema("博纳国际影城（中关村店）", "海淀区中关村大街19号", "010-55556666", "IMAX, 杜比全景声", "北京", "海淀区");
        Cinema c4 = cinema("UME国际影城（双井店）", "朝阳区东三环中路65号", "010-33336666", "IMAX, 中国巨幕, 4K", "北京", "朝阳区");

        // 影厅
        Hall h1 = hall(c1.getId(), "1号IMAX厅", "IMAX", "{\"rows\":8,\"cols\":12}");
        Hall h2 = hall(c1.getId(), "2号杜比厅", "杜比", "{\"rows\":6,\"cols\":10}");
        Hall h3 = hall(c1.getId(), "3号普通厅", "普通", "{\"rows\":5,\"cols\":8}");
        Hall h4 = hall(c2.getId(), "1号IMAX厅", "IMAX", "{\"rows\":10,\"cols\":14}");
        hall(c2.getId(), "2号普通厅", "普通", "{\"rows\":6,\"cols\":10}");
        hall(c3.getId(), "1号杜比厅", "杜比", "{\"rows\":8,\"cols\":12}");
        hall(c4.getId(), "1号IMAX厅", "IMAX", "{\"rows\":12,\"cols\":16}");
        hall(c4.getId(), "2号普通厅", "普通", "{\"rows\":6,\"cols\":10}");

        // 排片
        LocalDateTime now = LocalDateTime.now();
        show(m1.getId(), h1.getId(), c1.getId(), now.plusDays(1).withHour(14).withMinute(0), now.plusDays(1).withHour(17).withMinute(0), 69.9);
        show(m1.getId(), h2.getId(), c1.getId(), now.plusDays(1).withHour(19).withMinute(0), now.plusDays(1).withHour(22).withMinute(0), 49.9);
        show(m2.getId(), h3.getId(), c1.getId(), now.withHour(19).withMinute(30), now.withHour(22).withMinute(0), 39.9);
        show(m3.getId(), h4.getId(), c2.getId(), now.withHour(18).withMinute(0), now.withHour(20).withMinute(0), 59.9);
        show(m4.getId(), h1.getId(), c1.getId(), now.plusDays(2).withHour(14).withMinute(0), now.plusDays(2).withHour(17).withMinute(0), 69.9);

        // 影评
        review(user.getId(), m1.getId(), 5, "视觉震撼！诺兰再次刷新了我对科幻电影的认知。");
        review(user.getId(), m2.getId(), 5, "希望是美好的，也许是最美好的东西。");
    }

    private Movie movie(String title, String director, String cast, String type, String region, int year, String synopsis, double rating) {
        Movie m = new Movie();
        m.setTitle(title); m.setDirector(director); m.setCastList(cast); m.setType(type);
        m.setRegion(region); m.setYear(year); m.setSynopsis(synopsis); m.setRating(rating); m.setStatus("showing");
        return movieRepo.save(m);
    }
    private Cinema cinema(String name, String addr, String contact, String facilities, String city, String region) {
        Cinema c = new Cinema();
        c.setName(name); c.setAddress(addr); c.setContact(contact); c.setFacilities(facilities); c.setCity(city); c.setRegion(region);
        return cinemaRepo.save(c);
    }
    private Hall hall(Long cinemaId, String name, String type, String layout) {
        Hall h = new Hall();
        h.setCinemaId(cinemaId); h.setName(name); h.setType(type); h.setSeatLayout(layout);
        return hallRepo.save(h);
    }
    private void show(Long movieId, Long hallId, Long cinemaId, LocalDateTime start, LocalDateTime end, double price) {
        Show s = new Show();
        s.setMovieId(movieId); s.setHallId(hallId); s.setCinemaId(cinemaId);
        s.setStartTime(start); s.setEndTime(end); s.setPrice(price); s.setStatus("available");
        showRepo.save(s);
    }
    private void review(Long userId, Long movieId, int rating, String content) {
        Review r = new Review();
        r.setUserId(userId); r.setMovieId(movieId); r.setRating(rating); r.setContent(content);
        reviewRepo.save(r);
    }
}
