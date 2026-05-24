package com.example.guizhou.controller;

import com.example.guizhou.entity.Attraction;
import com.example.guizhou.repository.AttractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AttractionRepository attractionRepository;

    @Override
    public void run(String... args) throws Exception {
        long count = attractionRepository.count();
        System.out.println("当前数据库景点数量: " + count);
        
        if (count == 0) {
            System.out.println("开始初始化景点数据...");
            initializeAttractions();
            System.out.println("景点数据初始化完成！");
        } else {
            System.out.println("数据库已有数据，跳过初始化");
        }
    }

    private void initializeAttractions() {
        Attraction[] attractions = new Attraction[]{
                createAttraction("黄果树瀑布", "安顺市", "自然景观", "中国最大的瀑布，世界著名大瀑布之一，高77.8米，宽101米", "https://picsum.photos/seed/huangguoshu/800/600", 4.8, 150.0, "08:00-18:00", "四季皆宜"),
                createAttraction("荔波小七孔", "黔南州荔波县", "自然景观", "世界自然遗产地，以喀斯特地貌和原始森林为特色", "https://picsum.photos/seed/xiaoqikong/800/600", 4.7, 120.0, "08:30-17:00", "春夏秋"),
                createAttraction("西江千户苗寨", "黔东南州雷山县", "民族文化", "中国最大的苗族聚居村寨，被誉为'苗族露天博物馆'", "https://picsum.photos/seed/miaozhai/800/600", 4.6, 98.0, "全天", "四季皆宜"),
                createAttraction("梵净山", "铜仁市", "自然景观", "世界自然遗产，佛教名山，海拔2572米", "https://picsum.photos/seed/fanjingshan/800/600", 4.9, 110.0, "08:00-17:00", "春夏秋"),
                createAttraction("镇远古镇", "黔东南州镇远县", "历史文化", "中国历史文化名城，有2000多年历史", "https://picsum.photos/seed/zhenyuan/800/600", 4.5, 75.0, "全天", "四季皆宜"),
                createAttraction("织金洞", "毕节市织金县", "自然景观", "中国最美旅游洞穴第一名，世界地质公园", "https://picsum.photos/seed/zhijindong/800/600", 4.7, 85.0, "08:30-17:30", "四季皆宜"),
                createAttraction("龙宫", "安顺市", "自然景观", "中国最长、最美的水溶洞，国家5A级景区", "https://picsum.photos/seed/longgong/800/600", 4.4, 68.0, "08:00-17:30", "四季皆宜"),
                createAttraction("赤水丹霞", "遵义市赤水市", "自然景观", "世界自然遗产，中国面积最大的丹霞地貌", "https://picsum.photos/seed/chishui/800/600", 4.6, 72.0, "08:00-18:00", "春夏秋"),
                createAttraction("青岩古镇", "贵阳市花溪区", "历史文化", "贵州四大古镇之一，明清古建筑保存完好", "https://picsum.photos/seed/qingyan/800/600", 4.3, 88.0, "全天", "四季皆宜"),
                createAttraction("百里杜鹃", "毕节市大方县", "自然景观", "世界上最大的天然花园，杜鹃花面积125.8平方公里", "https://picsum.photos/seed/dujuan/800/600", 4.5, 65.0, "08:00-18:00", "春季"),
                createAttraction("肇兴侗寨", "黔东南州黎平县", "民族文化", "全国最大的侗族村寨，有'侗乡第一寨'之美誉", "https://picsum.photos/seed/dongzhai/800/600", 4.4, 52.0, "全天", "四季皆宜"),
                createAttraction("黔灵山公园", "贵阳市", "自然景观", "贵阳市区最大的综合性公园，有'黔南第一山'之称", "https://picsum.photos/seed/qianlingshan/800/600", 4.2, 120.0, "06:30-22:00", "四季皆宜"),
                createAttraction("遵义会议会址", "遵义市", "历史文化", "中国革命历史转折点，全国重点文物保护单位", "https://picsum.photos/seed/zunyi/800/600", 4.6, 95.0, "09:00-17:00", "四季皆宜"),
                createAttraction("马岭河峡谷", "黔西南州兴义市", "自然景观", "地球上最美丽的疤痕，峡谷长74.8公里", "https://picsum.photos/seed/malinghe/800/600", 4.5, 58.0, "08:00-18:00", "春夏秋"),
                createAttraction("万峰林", "黔西南州兴义市", "自然景观", "中国最美五大峰林之一，徐霞客曾游历此地", "https://picsum.photos/seed/wanfenglin/800/600", 4.7, 71.0, "08:00-18:00", "四季皆宜"),
                createAttraction("天眼FAST", "黔南州平塘县", "科技景观", "世界最大单口径射电望远镜，直径500米", "https://picsum.photos/seed/fast/800/600", 4.8, 82.0, "09:00-17:00", "四季皆宜"),
                createAttraction("雷公山", "黔东南州雷山县", "自然景观", "国家级自然保护区，主峰海拔2178.8米", "https://picsum.photos/seed/leigongshan/800/600", 4.3, 42.0, "08:00-17:00", "春夏秋"),
                createAttraction("加榜梯田", "黔东南州从江县", "自然景观", "中国三大梯田之一，苗族人民创造的农耕文明奇观", "https://picsum.photos/seed/jiabang/800/600", 4.4, 38.0, "全天", "春夏秋"),
                createAttraction("红枫湖", "贵阳市清镇市", "自然景观", "贵州高原最大的人工湖，水域面积57平方公里", "https://picsum.photos/seed/hongfenghu/800/600", 4.2, 67.0, "08:00-18:00", "四季皆宜"),
                createAttraction("天河潭", "贵阳市花溪区", "自然景观", "集溶洞、瀑布、湖泊于一体的风景区", "https://picsum.photos/seed/tianhetan/800/600", 4.1, 55.0, "08:30-17:30", "四季皆宜"),
                createAttraction("丙安古镇", "遵义市赤水市", "历史文化", "中国历史文化名村，红军四渡赤水纪念地", "https://picsum.photos/seed/bingan/800/600", 4.3, 35.0, "全天", "四季皆宜"),
                createAttraction("郎德苗寨", "黔东南州雷山县", "民族文化", "中国民间歌舞艺术之乡，苗族文化保存完整", "https://picsum.photos/seed/langde/800/600", 4.2, 28.0, "全天", "四季皆宜"),
                createAttraction("茂兰喀斯特森林", "黔南州荔波县", "自然景观", "世界自然遗产地，保存完好的喀斯特原始森林", "https://picsum.photos/seed/maolan/800/600", 4.4, 31.0, "08:30-17:00", "春夏秋"),
                createAttraction("乌江源百里画廊", "毕节市黔西县", "自然景观", "集自然风光和人文景观于一体的水上旅游线路", "https://picsum.photos/seed/wujiang/800/600", 4.3, 44.0, "08:00-18:00", "春夏秋"),
                createAttraction("格凸河", "安顺市紫云县", "自然景观", "国际攀岩圣地，喀斯特地貌典型代表", "https://picsum.photos/seed/getuhe/800/600", 4.2, 26.0, "08:00-17:30", "春夏秋"),
                createAttraction("海龙屯", "遵义市", "历史文化", "世界文化遗产，中国古代军事城堡遗址", "https://picsum.photos/seed/hailongtun/800/600", 4.5, 39.0, "08:30-17:30", "四季皆宜"),
                createAttraction("花溪公园", "贵阳市花溪区", "自然景观", "贵阳著名风景区，巴金曾在此创作《憩园》", "https://picsum.photos/seed/huaxi/800/600", 4.0, 92.0, "全天", "四季皆宜"),
                createAttraction("息烽集中营", "贵阳市息烽县", "历史文化", "全国重点文物保护单位，爱国主义教育基地", "https://picsum.photos/seed/xifeng/800/600", 4.4, 41.0, "09:00-17:00", "四季皆宜"),
                createAttraction("舞阳河", "黔东南州镇远县", "自然景观", "国家级风景名胜区，以峡谷风光著称", "https://picsum.photos/seed/wuyanghe/800/600", 4.3, 47.0, "08:00-18:00", "春夏秋"),
                createAttraction("六盘水玉舍森林公园", "六盘水市", "自然景观", "国家4A级景区，夏季避暑胜地", "https://picsum.photos/seed/yushe/800/600", 4.1, 33.0, "全天", "夏季"),
                createAttraction("韭菜坪", "毕节市赫章县", "自然景观", "贵州最高峰，海拔2900.6米，夏季避暑胜地", "https://picsum.photos/seed/jiucaiping/800/600", 4.2, 29.0, "08:00-17:00", "夏季"),
                createAttraction("妥乐古银杏", "六盘水市盘州市", "自然景观", "世界古银杏之乡，有古银杏树1200余株", "https://picsum.photos/seed/tuole/800/600", 4.4, 36.0, "08:00-18:00", "秋季"),
                createAttraction("云舍土家民俗村", "铜仁市江口县", "民族文化", "保留完整的土家族传统村落", "https://picsum.photos/seed/yunshe/800/600", 4.1, 22.0, "全天", "四季皆宜"),
                createAttraction("石阡温泉", "铜仁市石阡县", "休闲度假", "中国温泉之乡，天然矿泉水温泉", "https://picsum.photos/seed/shiqian/800/600", 4.3, 48.0, "全天", "四季皆宜"),
                createAttraction("太平河", "铜仁市江口县", "自然景观", "梵净山脚下的美丽河流，水质清澈", "https://picsum.photos/seed/taipinghe/800/600", 4.0, 25.0, "全天", "春夏秋"),
                createAttraction("万山汞矿遗址", "铜仁市万山区", "历史文化", "中国汞都遗址，工业文化遗产", "https://picsum.photos/seed/wanshan/800/600", 4.2, 18.0, "09:00-17:00", "四季皆宜"),
                createAttraction("旧州古镇", "安顺市西秀区", "历史文化", "贵州四大古镇之一，屯堡文化发源地", "https://picsum.photos/seed/jiuzhou/800/600", 4.3, 32.0, "全天", "四季皆宜"),
                createAttraction("天龙屯堡", "安顺市平坝区", "历史文化", "明代屯军后裔居住地，保留明代遗风", "https://picsum.photos/seed/tianlong/800/600", 4.2, 27.0, "08:30-17:30", "四季皆宜"),
                createAttraction("格凸河穿洞", "安顺市紫云县", "自然景观", "天然穿洞奇观，阳光照射形成天地神光", "https://picsum.photos/seed/chuandong/800/600", 4.4, 21.0, "08:00-17:30", "春夏秋"),
                createAttraction("坝陵河大桥", "安顺市", "科技景观", "世界第二高桥，桥高370米", "https://picsum.photos/seed/balinghe/800/600", 4.5, 45.0, "全天", "四季皆宜")
        };

        for (Attraction attraction : attractions) {
            attractionRepository.save(attraction);
        }
        System.out.println("成功初始化 " + attractions.length + " 个贵州景点数据");
    }

    private Attraction createAttraction(String name, String location, String category, 
                                       String description, String imageUrl, Double rating,
                                       Double ticketPrice, String openingHours, 
                                       String bestSeason) {
        Attraction attraction = new Attraction();
        attraction.setName(name);
        attraction.setLocation(location);
        attraction.setCategory(category);
        attraction.setDescription(description);
        attraction.setImageUrl(imageUrl);
        attraction.setRating(rating);
        attraction.setTicketPrice(ticketPrice);
        attraction.setOpeningHours(openingHours);
        attraction.setBestSeason(bestSeason);
        attraction.setViewCount((int)(Math.random() * 10000));
        return attraction;
    }
}
