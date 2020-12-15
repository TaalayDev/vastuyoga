package kg.vy.models

class VideoPropsModel(name: String, title: String, link: String) {
    var name: String = name
    var title: String = title
    var link: String = link

    var hour = 0
    var minute = 0

    companion object {
        private const val vasudevaLink = "ZGgfo96bXcc"
        private const val narasimhaLink = "8XthizAtqXA"
        private const val buddhadevaLink = "UGCwbXEpBdA"
        private const val vamanadevaLink = "7991g5OtMlc"
        private const val matsyadevaLink = "kkVKe2rXAs8"
        private const val parasuramaLink = "iBmBZZxvWN4"
        private const val kurmadevaLink = "LqrULwH7h6A"
        private const val varahadevaLink = "_LgLnT2vlMo"
        private const val ramachandraLink = "6YRnXwYRy3o"
        private const val lakshmiLink = "kLMEU2xuaAM"
        private const val brahmaLink = "sPPSLiQzN1I"
        private const val vastupurushaLink = "0ukpzSN-5rI"

        val menu: Map<String, List<VideoPropsModel>> = mapOf(
            "en" to listOf(
                VideoPropsModel("Vasudeva", "Travel", vasudevaLink),
                VideoPropsModel("Vasudeva", "Changes", vasudevaLink),
                VideoPropsModel("Vasudeva", "Mother", vasudevaLink),

                VideoPropsModel("Narasimha", "Will", narasimhaLink),
                VideoPropsModel("Narasimha", "Victory", narasimhaLink),
                VideoPropsModel("Narasimha", "Desires fulfilment", narasimhaLink),
                VideoPropsModel("Narasimha", "Struggle", narasimhaLink),
                VideoPropsModel("Narasimha", "Masculine energy", narasimhaLink),
                VideoPropsModel("Narasimha", "Male Sexuality", narasimhaLink),

                VideoPropsModel("Buddha Deva", "Communication", buddhadevaLink),
                VideoPropsModel("Buddha Deva", "Friendship", buddhadevaLink),
                VideoPropsModel("Buddha Deva", "Financial flows", buddhadevaLink),
                VideoPropsModel("Buddha Deva", "Business humour", buddhadevaLink),
                VideoPropsModel("Buddha Deva", "sense Logic", buddhadevaLink),

                VideoPropsModel("Vamana Deva", "Study", vamanadevaLink),
                VideoPropsModel(
                    "Vamana Deva", "Knowledge", vamanadevaLink
                ),
                VideoPropsModel(
                    "Vamana Deva", "Spiritual life", vamanadevaLink
                ),
                VideoPropsModel(
                    "Vamana Deva", "Teaching", vamanadevaLink
                ),

                VideoPropsModel(
                    "Matsya Deva", "Intuition", matsyadevaLink
                ),
                VideoPropsModel(
                    "Matsya Deva", "Wisdom", matsyadevaLink
                ),
                VideoPropsModel(
                    "Matsya Deva", "Restrictions removal", matsyadevaLink
                ),

                VideoPropsModel(
                    "Parasurama", "Beauty", parasuramaLink
                ),
                VideoPropsModel(
                    "Parasurama", "Pleasure", parasuramaLink
                ),
                VideoPropsModel(
                    "Parasurama", "Art", parasuramaLink
                ),
                VideoPropsModel(
                    "Parasurama", "Luxury", parasuramaLink
                ),

                VideoPropsModel(
                    "Kurma Deva", "Patience", kurmadevaLink
                ),
                VideoPropsModel(
                    "Kurma Deva", "Asceticism", kurmadevaLink
                ),
                VideoPropsModel(
                    "Kurma Deva", "Depth", kurmadevaLink
                ),
                VideoPropsModel(
                    "Kurma Deva", "Old age", kurmadevaLink
                ),
                VideoPropsModel(
                    "Kurma Deva", "Ability to endure any test", kurmadevaLink
                ),

                VideoPropsModel(
                    "Varaha Deva", "The ability to see through the illusion", varahadevaLink
                ),
                VideoPropsModel(
                    "Varaha Deva", "Freedom from drug addiction and alcoholism", varahadevaLink
                ),

                VideoPropsModel(
                    "Ramachandra", "Glory", ramachandraLink
                ),
                VideoPropsModel(
                    "Ramachandra", "Beginnings",
                    ramachandraLink
                ),
                VideoPropsModel(
                    "Ramachandra", "Father", ramachandraLink
                ),
                VideoPropsModel(
                    "Ramachandra", "Career growth", ramachandraLink
                ),
                VideoPropsModel(
                    "Ramachandra", "Ability to lead", ramachandraLink
                ),
                VideoPropsModel(
                    "Ramachandra", "Startups", ramachandraLink
                ),

                VideoPropsModel(
                    "Lakshmi", "Luck", lakshmiLink
                ),
                VideoPropsModel(
                    "Lakshmi", "Rapid spiritual growth", lakshmiLink
                ),
                VideoPropsModel(
                    "Lakshmi", "Women\"s sexuality", lakshmiLink
                ),
                VideoPropsModel(
                    "Lakshmi", "Women\"s beauty", lakshmiLink
                ),

                VideoPropsModel(
                    "Brahma", "Potential", brahmaLink
                ),
                VideoPropsModel(
                    "Brahma", "New on the subtle plane", brahmaLink
                ),

                VideoPropsModel(
                    "Vastu Purusha", "Building protection",  vastupurushaLink
                )
            ),
            "ru" to listOf(
                VideoPropsModel(
                    "Васудева", "Путешествия",
                    vasudevaLink
                ),
                VideoPropsModel(
                    "Васудева", "Изменения",
                    vasudevaLink
                ),
                VideoPropsModel(
                    "Васудева", "Мать",
                    vasudevaLink
                ),

                VideoPropsModel(
                    "Нарасимха", "Воля",
                    narasimhaLink
                ),
                VideoPropsModel(
                    "Нарасимха", "Победа",
                    narasimhaLink
                ),
                VideoPropsModel(
                    "Нарасимха", "Исполнения желания",
                    narasimhaLink
                ),
                VideoPropsModel(
                    "Нарасимха", "Борьба",
                    narasimhaLink
                ),
                VideoPropsModel(
                    "Нарасимха", "Мужская энергия",
                    narasimhaLink
                ),
                VideoPropsModel(
                    "Нарасимха", "Мужская сексуальность",
                    narasimhaLink
                ),

                VideoPropsModel(
                    "Буддха дева", "Коммуникации",
                    buddhadevaLink
                ),
                VideoPropsModel(
                    "Буддха дева", "Дружба",
                    buddhadevaLink
                ),
                VideoPropsModel(
                    "Буддха дева", "Финансовые потоки",
                    buddhadevaLink
                ),
                VideoPropsModel(
                    "Буддха дева", "Бизнес",
                    buddhadevaLink
                ),
                VideoPropsModel(
                    "Буддха дева", "Чувство юмора",
                    buddhadevaLink
                ),
                VideoPropsModel(
                    "Буддха дева", "Логика",
                    buddhadevaLink
                ),

                VideoPropsModel(
                    "Вамана дева", "Учеба",
                    vamanadevaLink
                ),
                VideoPropsModel(
                    "Вамана дева", "Знания",
                    vamanadevaLink
                ),
                VideoPropsModel(
                    "Вамана дева", "Духовная жизнь",
                    vamanadevaLink
                ),
                VideoPropsModel(
                    "Вамана дева", "Преподавание",
                    vamanadevaLink
                ),

                VideoPropsModel(
                    "Матсья дева", "Интуиция",
                    matsyadevaLink
                ),
                VideoPropsModel(
                    "Матсья дева", "Мудрость снятие ограничений",
                    matsyadevaLink
                ),

                VideoPropsModel(
                    "Парасурама", "Красота",
                    parasuramaLink
                ),
                VideoPropsModel(
                    "Парасурама", "Наслаждения",
                    parasuramaLink
                ),
                VideoPropsModel(
                    "Парасурама", "Искусства",
                    parasuramaLink
                ),
                VideoPropsModel(
                    "Парасурама", "Роскошь",
                    parasuramaLink
                ),

                VideoPropsModel(
                    "Курма дева", "Терпение",
                    kurmadevaLink
                ),
                VideoPropsModel(
                    "Курма дева", "Аскетичность",
                    kurmadevaLink
                ),
                VideoPropsModel(
                    "Курма дева", "Глубина старость",
                    kurmadevaLink
                ),
                VideoPropsModel(
                    "Курма дева", "Способность вынести любые испытания",
                    kurmadevaLink
                ),

                VideoPropsModel(
                    "Вараха дева", "Способность видеть сквозь иллюзию",
                    varahadevaLink
                ),
                VideoPropsModel(
                    "Вараха дева", "Свобода от наркомании и алкоголизма",
                    varahadevaLink
                ),

                VideoPropsModel(
                    "Рамачандра", "Слава",
                    ramachandraLink
                ),
                VideoPropsModel(
                    "Рамачандра", "Начинания",
                    ramachandraLink
                ),
                VideoPropsModel(
                    "Рамачандра", "Отец",
                    ramachandraLink
                ),
                VideoPropsModel(
                    "Рамачандра", "Карьерный рост",
                    ramachandraLink
                ),
                VideoPropsModel(
                    "Рамачандра", "Умение вести за собой",
                    ramachandraLink
                ),
                VideoPropsModel(
                    "Рамачандра", "Стартапы",
                    ramachandraLink
                ),

                VideoPropsModel(
                    "Лакшми", "Удача",
                    lakshmiLink
                ),
                VideoPropsModel(
                    "Лакшми", "Быстрый духовный рост",
                    lakshmiLink
                ),
                VideoPropsModel(
                    "Лакшми", "Женскаяя сексуальность",
                    lakshmiLink
                ),
                VideoPropsModel(
                    "Лакшми", "Женская красота",
                    lakshmiLink
                ),

                VideoPropsModel(
                    "Брахма", "Потенциал",
                    brahmaLink
                ),
                VideoPropsModel(
                    "Брахма", "Новое на тонком плане",
                    brahmaLink
                ),

                VideoPropsModel(
                    "Васту пуруша", "Защита здания",
                    vastupurushaLink
                )
            ),
            "ja" to listOf(
                VideoPropsModel(
                    "ヴァスデーヴァ", "旅行",
                    vasudevaLink
                ),
                VideoPropsModel(
                    "ヴァスデーヴァ", "変化",
                    vasudevaLink
                ),
                VideoPropsModel(
                    "ヴァスデーヴァ", "母",
                    vasudevaLink
                ),

                VideoPropsModel(
                    "ナラシンハ", "気力",
                    narasimhaLink
                ),
                VideoPropsModel(
                    "ナラシンハ", "勝",
                    narasimhaLink
                ),
                VideoPropsModel(
                    "ナラシンハ", "如意",
                    narasimhaLink
                ),
                VideoPropsModel(
                    "ナラシンハ", "係争",
                    narasimhaLink
                ),
                VideoPropsModel(
                    "ナラシンハ", "男性のエネルギー",
                    narasimhaLink
                ),
                VideoPropsModel(
                    "ナラシンハ", "男性 の性",
                    narasimhaLink
                ),

                VideoPropsModel(
                    "仏乙 女", "コミュニケーション",
                    buddhadevaLink
                ),
                VideoPropsModel(
                    "仏乙 女", "友情",
                    buddhadevaLink
                ),
                VideoPropsModel(
                    "仏乙 女", "財務フロー",
                    buddhadevaLink
                ),
                VideoPropsModel(
                    "仏乙 女", "ビジネス",
                    buddhadevaLink
                ),
                VideoPropsModel(
                    "仏乙 女", "ユーモアのセンス",
                    buddhadevaLink
                ),
                VideoPropsModel(
                    "仏乙 女", "ロジック",
                    buddhadevaLink
                ),

                VideoPropsModel(
                    "ヴァーマナ女", "研究",
                    vamanadevaLink
                ),
                VideoPropsModel(
                    "ヴァーマナ女", "知識",
                    vamanadevaLink
                ),
                VideoPropsModel(
                    "ヴァーマナ女", "精 神的な生活",
                    vamanadevaLink
                ),
                VideoPropsModel(
                    "ヴァーマナ女", "教え",
                    vamanadevaLink
                ),

                VideoPropsModel(
                    "マツヤ女", "直感",
                    matsyadevaLink
                ),
                VideoPropsModel(
                    "マツヤ女", "知恵",
                    matsyadevaLink
                ),
                VideoPropsModel(
                    "マツヤ女", "持ち上 げ制限",
                    matsyadevaLink
                ),

                VideoPropsModel(
                    "パラスラマ", "美しさ",
                    parasuramaLink
                ),
                VideoPropsModel(
                    "パラスラマ", "喜び",
                    parasuramaLink
                ),
                VideoPropsModel(
                    "パラスラマ", "芸 術",
                    parasuramaLink
                ),
                VideoPropsModel(
                    "パラスラマ", "贅沢",
                    parasuramaLink
                ),

                VideoPropsModel(
                    "クールマ女", "忍耐",
                    kurmadevaLink
                ),
                VideoPropsModel(
                    "クールマ女", "禁欲主義",
                    kurmadevaLink
                ),
                VideoPropsModel(
                    "クールマ女", "深さ",
                    kurmadevaLink
                ),
                VideoPropsModel(
                    "クールマ女", "老齢",
                    kurmadevaLink
                ),
                VideoPropsModel(
                    "クールマ女", "あらゆる試練に耐える能力",
                    kurmadevaLink
                ),

                VideoPropsModel(
                    "ヴァラーハ女", "幻想を通して見る能力",
                    varahadevaLink
                ),
                VideoPropsModel(
                    "ヴァラーハ女", "薬物やアルコール中毒からの解放",
                    varahadevaLink
                ),

                VideoPropsModel(
                    "ラクシュミ", "運",
                    lakshmiLink
                ),
                VideoPropsModel(
                    "ラクシュミ", "急速な精神的成長",
                    lakshmiLink
                ),
                VideoPropsModel(
                    "ラクシュミ", "女性の性",
                    lakshmiLink
                ),
                VideoPropsModel(
                    "ラクシュミ", "女性の美しさ",
                    lakshmiLink
                ),

                VideoPropsModel(
                    "ブラフマ", "可能性",
                    brahmaLink
                ),
                VideoPropsModel(
                    "ブラフマ", "新着",
                    brahmaLink
                ),

                VideoPropsModel(
                    "ヴァーストゥ・プルシャ", "建築の防衛",
                    vastupurushaLink
                )
            ),
            "ko" to listOf(
                VideoPropsModel(
                    "바수데바 아난카둔두히", "인생의 길",
                    vasudevaLink
                ),
                VideoPropsModel(
                    "바수데바 아난카둔두히", "변화",
                    vasudevaLink
                ),
                VideoPropsModel(
                    "바수데바 아난카둔두히", "어머니",
                    vasudevaLink
                ),

                VideoPropsModel(
                    "비슈누나라싱하", "의지",
                    narasimhaLink
                ),
                VideoPropsModel(
                    "비슈누나라싱하", "승리",
                    narasimhaLink
                ),
                VideoPropsModel(
                    "비슈누나라싱하", "소원의 성취",
                    narasimhaLink
                ),
                VideoPropsModel(
                    "비슈누나라싱하", "고생",
                    narasimhaLink
                ),
                VideoPropsModel(
                    "비슈누나라싱하", "양의 기운",
                    narasimhaLink
                ),

                VideoPropsModel(
                    "데바로카", "인간관계",
                    buddhadevaLink
                ),
                VideoPropsModel(
                    "데바로카", "우정",
                    buddhadevaLink
                ),
                VideoPropsModel(
                    "데바로카", "재산",
                    buddhadevaLink
                ),
                VideoPropsModel(
                    "데바로카", "사업",
                    buddhadevaLink
                ),
                VideoPropsModel(
                    "데바로카", "유머감각 논리",
                    buddhadevaLink
                ),

                VideoPropsModel(
                    "바마나", "공부",
                    vamanadevaLink
                ),
                VideoPropsModel(
                    "바마나", "지식",
                    vamanadevaLink
                ),
                VideoPropsModel(
                    "바마나", "영성 생활",
                    vamanadevaLink
                ),
                VideoPropsModel(
                    "바마나", "교육",
                    vamanadevaLink
                ),

                VideoPropsModel(
                    "마츠야", "직감",
                    matsyadevaLink
                ),
                VideoPropsModel(
                    "마츠야", "지혜",
                    matsyadevaLink
                ),
                VideoPropsModel(
                    "마츠야", "규제완화",
                    matsyadevaLink
                ),

                VideoPropsModel(
                    "파라슈라마", "미",
                    parasuramaLink
                ),
                VideoPropsModel(
                    "파라슈라마", "기쁨",
                    parasuramaLink
                ),
                VideoPropsModel(
                    "파라슈라마", "예술",
                    parasuramaLink
                ),
                VideoPropsModel(
                    "파라슈라마", "사치",
                    parasuramaLink
                ),

                VideoPropsModel(
                    "쿠르마", "인내심",
                    kurmadevaLink
                ),
                VideoPropsModel(
                    "쿠르마", "금욕",
                    kurmadevaLink
                ),
                VideoPropsModel(
                    "쿠르마", "장생",
                    kurmadevaLink
                ),
                VideoPropsModel(
                    "쿠르마", "끈기",
                    kurmadevaLink
                ),

                VideoPropsModel(
                    "바라하", "환상을 깨지는 능력",
                    varahadevaLink
                ),
                VideoPropsModel(
                    "바라하", "마약 중독 및 알코올 중독을 처리",
                    varahadevaLink
                ),

                VideoPropsModel(
                    "라마찬드라", "영예",
                    ramachandraLink
                ),
                VideoPropsModel(
                    "라마찬드라", "새로운 시작",
                    ramachandraLink
                ),
                VideoPropsModel(
                    "라마찬드라", "아버지",
                    ramachandraLink
                ),
                VideoPropsModel(
                    "라마찬드라", "경력 성장",
                    ramachandraLink
                ),
                VideoPropsModel(
                    "라마찬드라", "리더십",
                    ramachandraLink
                ),
                VideoPropsModel(
                    "라마찬드라", "스타트업",
                    ramachandraLink
                ),

                VideoPropsModel(
                    "락슈미", "행운",
                    lakshmiLink
                ),
                VideoPropsModel(
                    "락슈미", "정신적 성장",
                    lakshmiLink
                ),
                VideoPropsModel(
                    "락슈미", "여성의 성육",
                    lakshmiLink
                ),
                VideoPropsModel(
                    "락슈미", "여성의 미",
                    lakshmiLink
                ),

                VideoPropsModel(
                    "브라흐마", "잠재력",
                    brahmaLink
                ),
                VideoPropsModel(
                    "브라흐마", "미묘한 면에서 새로운 시작",
                    brahmaLink
                ),

                VideoPropsModel(
                    "바스투-푸루샤", "건물 안정",
                    vastupurushaLink
                )
            ),
            "zn" to listOf(
                VideoPropsModel(
                    "瓦苏德瓦", "母亲",
                    vasudevaLink
                ),
                VideoPropsModel(
                    "瓦苏德瓦", "的改变",
                    vasudevaLink
                ),
                VideoPropsModel(
                    "瓦苏德瓦", "之旅",
                    vasudevaLink
                ),

                VideoPropsModel(
                    "纳拉西米哈", "意志",
                    narasimhaLink
                ),
                VideoPropsModel(
                    "纳拉西米哈", "胜利",
                    narasimhaLink
                ),
                VideoPropsModel(
                    "纳拉西米哈", "愿望实现",
                    narasimhaLink
                ),
                VideoPropsModel(
                    "纳拉西米哈", "奋斗",
                    narasimhaLink
                ),
                VideoPropsModel(
                    "纳拉西米哈", "男性活力",
                    narasimhaLink
                ),
                VideoPropsModel(
                    "纳拉西米哈", "男性性行为",
                    narasimhaLink
                ),

                VideoPropsModel(
                    "佛陀 德瓦", "交流",
                    buddhadevaLink
                ),
                VideoPropsModel(
                    "佛陀 德瓦", "友谊",
                    buddhadevaLink
                ),
                VideoPropsModel(
                    "佛陀 德瓦", "金融流动",
                    buddhadevaLink
                ),
                VideoPropsModel(
                    "佛陀 德瓦", "商业幽默感",
                    buddhadevaLink
                ),
                VideoPropsModel(
                    "佛陀 德瓦", "逻辑",
                    buddhadevaLink
                ),

                VideoPropsModel(
                    "瓦玛娜·德瓦", "知识",
                    vamanadevaLink
                ),
                VideoPropsModel(
                    "瓦玛娜·德瓦", "学习",
                    vamanadevaLink
                ),
                VideoPropsModel(
                    "瓦玛娜·德瓦", "与精神生活",
                    vamanadevaLink
                ),
                VideoPropsModel(
                    "瓦玛娜·德瓦", "教学",
                    vamanadevaLink
                ),

                VideoPropsModel(
                    "马斯佳 德瓦", "直觉",
                    matsyadevaLink
                ),
                VideoPropsModel(
                    "马斯佳 德瓦", "智慧",
                    matsyadevaLink
                ),
                VideoPropsModel(
                    "马斯佳 德瓦", "限制解除",
                    matsyadevaLink
                ),

                VideoPropsModel(
                    "极乐世界", "美容",
                    parasuramaLink
                ),
                VideoPropsModel(
                    "极乐世界", "娱乐",
                    parasuramaLink
                ),
                VideoPropsModel(
                    "极乐世界", "艺术",
                    parasuramaLink
                ),
                VideoPropsModel(
                    "极乐世界", "奢华",
                    parasuramaLink
                ),

                VideoPropsModel(
                    "库勒玛 德瓦", "耐性",
                    kurmadevaLink
                ),
                VideoPropsModel(
                    "库勒玛 德瓦", "年龄的深度",
                    kurmadevaLink
                ),
                VideoPropsModel(
                    "库勒玛 德瓦", "经受任何考验的能力",
                    kurmadevaLink
                ),

                VideoPropsModel(
                    "瓦拉玛 德瓦", "透过幻觉察看东西的能力",
                    varahadevaLink
                ),
                VideoPropsModel(
                    "瓦拉玛 德瓦", "摆脱吸毒和酗酒",
                    varahadevaLink
                ),

                VideoPropsModel(
                    "拉玛察特拉", "成功",
                    ramachandraLink
                ),
                VideoPropsModel(
                    "拉玛察特拉", "事业",
                    ramachandraLink
                ),
                VideoPropsModel(
                    "拉玛察特拉", "创业之父",
                    ramachandraLink
                ),

                VideoPropsModel(
                    "拉克希米", "幸运的是",
                    lakshmiLink
                ),
                VideoPropsModel(
                    "拉克希米", "女性的性欲迅速增长",
                    lakshmiLink
                ),
                VideoPropsModel(
                    "拉克希米", "女性的美貌",
                    lakshmiLink
                ),

                VideoPropsModel(
                    "梵天", "潜力",
                    brahmaLink
                ),
                VideoPropsModel(
                    "梵天", "在微妙的平面上是新的",
                    brahmaLink
                ),

                VideoPropsModel(
                    "瓦斯度布鲁沙", "保护建筑物",
                    vastupurushaLink
                )
            )
        )
    }

}