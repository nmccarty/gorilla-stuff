;; gorilla-repl.fileformat = 1

;; @@
(ns economy-health
  (:require [gorilla-plot.core :as plot]
            [clojure.string :as string]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(def player-file (slurp "/home/nmccarty/players.csv"))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;economy-health/player-file</span>","value":"#'economy-health/player-file"}
;; <=

;; **
;;; This piece of code intreprets and filters the data given to it.
;; **

;; @@
(def monies (->> player-file
                 (string/split-lines)
                 (map #(string/split % #";"))
                 (drop 26)
                 (map last)
                 (map #(Long/parseLong %))
                 (filter #(every? true?
                                  (map (fn [x] (not= x %1))
                                       [500 530 545 560 590 620 635 650 630])))
                 (filter #(> % 1))
                 (filter #(< % 1000000))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;economy-health/monies</span>","value":"#'economy-health/monies"}
;; <=

;; **
;;; The number of data points left after filtering.
;; **

;; @@
(count monies)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>270</span>","value":"270"}
;; <=

;; **
;;; The first 10 elements of the list of data. I did this just to make sure it looked sane.
;; **

;; @@
(take 10 monies)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>152065</span>","value":"152065"},{"type":"html","content":"<span class='clj-long'>815</span>","value":"815"},{"type":"html","content":"<span class='clj-long'>1707</span>","value":"1707"},{"type":"html","content":"<span class='clj-long'>790</span>","value":"790"},{"type":"html","content":"<span class='clj-long'>3275</span>","value":"3275"},{"type":"html","content":"<span class='clj-long'>7890</span>","value":"7890"},{"type":"html","content":"<span class='clj-long'>175613</span>","value":"175613"},{"type":"html","content":"<span class='clj-long'>223465</span>","value":"223465"},{"type":"html","content":"<span class='clj-long'>540817</span>","value":"540817"},{"type":"html","content":"<span class='clj-long'>609296</span>","value":"609296"}],"value":"(152065 815 1707 790 3275 7890 175613 223465 540817 609296)"}
;; <=

;; **
;;; The most common ammount of money in the data set and how many times it appears.
;; **

;; @@
(reduce (fn [[x1 x2] [y1 y2]]
          (if (> x2 y2)
            [x1 x2]
            [y1 y2]))
        (frequencies monies))
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-vector'>[</span>","close":"<span class='clj-vector'>]</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>680</span>","value":"680"},{"type":"html","content":"<span class='clj-long'>9</span>","value":"9"}],"value":"[680 9]"}
;; <=

;; **
;;; The highest data in the set.
;; **

;; @@
(reduce max monies)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-long'>959035</span>","value":"959035"}
;; <=

;; **
;;; The lowest data in the set.
;; **

;; @@
(reduce min monies)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-long'>2</span>","value":"2"}
;; <=

;; **
;;; The following is the distribution of wealth overlayed with a translated graph of 1/x.
;;; The x axis is money, and the y axis is the number of players with that ammount of money.
;; **

;; @@
(plot/compose
 (plot/histogram monies 
                :bins 57
                :plot-range [[0 6700] :all])
 (plot/plot #(/ (/ (- % 549) 3300)) [550 6700]))
;; @@
;; =>
;;; {"type":"vega","content":{"width":400,"height":247.2187957763672,"padding":{"bottom":20,"top":10,"right":10,"left":50},"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":[0,6700]},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"2077d49b-3fa7-4f54-9024-2f77290bb108","field":"data.y"}}],"axes":[{"scale":"x","type":"x"},{"scale":"y","type":"y"}],"data":[{"name":"2077d49b-3fa7-4f54-9024-2f77290bb108","values":[{"x":0.0,"y":0},{"x":117.54385964912282,"y":9.0},{"x":235.08771929824564,"y":11.0},{"x":352.63157894736844,"y":15.0},{"x":470.1754385964913,"y":12.0},{"x":587.7192982456141,"y":12.0},{"x":705.263157894737,"y":32.0},{"x":822.8070175438598,"y":26.0},{"x":940.3508771929827,"y":13.0},{"x":1057.8947368421054,"y":11.0},{"x":1175.4385964912283,"y":7.0},{"x":1292.9824561403511,"y":9.0},{"x":1410.526315789474,"y":6.0},{"x":1528.0701754385968,"y":5.0},{"x":1645.6140350877197,"y":4.0},{"x":1763.1578947368425,"y":5.0},{"x":1880.7017543859654,"y":2.0},{"x":1998.2456140350882,"y":3.0},{"x":2115.789473684211,"y":1.0},{"x":2233.3333333333335,"y":1.0},{"x":2350.877192982456,"y":2.0},{"x":2468.4210526315787,"y":1.0},{"x":2585.9649122807014,"y":2.0},{"x":2703.508771929824,"y":2.0},{"x":2821.0526315789466,"y":1.0},{"x":2938.596491228069,"y":0.0},{"x":3056.140350877192,"y":1.0},{"x":3173.6842105263145,"y":1.0},{"x":3291.228070175437,"y":1.0},{"x":3408.7719298245597,"y":0.0},{"x":3526.3157894736823,"y":0.0},{"x":3643.859649122805,"y":1.0},{"x":3761.4035087719276,"y":0.0},{"x":3878.94736842105,"y":0.0},{"x":3996.491228070173,"y":0.0},{"x":4114.035087719296,"y":0.0},{"x":4231.578947368419,"y":0.0},{"x":4349.122807017542,"y":2.0},{"x":4466.666666666665,"y":0.0},{"x":4584.210526315788,"y":0.0},{"x":4701.754385964911,"y":0.0},{"x":4819.298245614034,"y":0.0},{"x":4936.8421052631575,"y":1.0},{"x":5054.3859649122805,"y":0.0},{"x":5171.929824561404,"y":0.0},{"x":5289.473684210527,"y":0.0},{"x":5407.01754385965,"y":0.0},{"x":5524.561403508773,"y":0.0},{"x":5642.105263157896,"y":0.0},{"x":5759.649122807019,"y":1.0},{"x":5877.192982456142,"y":1.0},{"x":5994.736842105265,"y":0.0},{"x":6112.280701754388,"y":0.0},{"x":6229.824561403511,"y":1.0},{"x":6347.368421052634,"y":1.0},{"x":6464.9122807017575,"y":1.0},{"x":6582.456140350881,"y":0.0},{"x":6700.000000000004,"y":0.0},{"x":6817.543859649127,"y":0}]},{"name":"45302dca-2d46-40a7-b045-50b1e35a4467","values":[{"x":550,"y":3300},{"x":611.5,"y":52.8},{"x":673.0,"y":26.612903225806452},{"x":734.5,"y":17.78975741239892},{"x":796.0,"y":13.360323886639677},{"x":857.5,"y":10.696920583468394},{"x":919.0,"y":8.91891891891892},{"x":980.5,"y":7.647740440324449},{"x":1042.0,"y":6.69371196754564},{"x":1103.5,"y":5.951307484220018},{"x":1165.0,"y":5.357142857142857},{"x":1226.5,"y":4.8708487084870855},{"x":1288.0,"y":4.465493910690122},{"x":1349.5,"y":4.122423485321674},{"x":1411.0,"y":3.8283062645011596},{"x":1472.5,"y":3.573362208987547},{"x":1534.0,"y":3.3502538071065993},{"x":1595.5,"y":3.153368370759675},{"x":1657.0,"y":2.9783393501805056},{"x":1718.5,"y":2.821718683197948},{"x":1780.0,"y":2.680747359870024},{"x":1841.5,"y":2.5531914893617023},{"x":1903.0,"y":2.4372230428360413},{"x":1964.5,"y":2.3313316849169903},{"x":2026.0,"y":2.2342586323628977},{"x":2087.5,"y":2.1449463763405916},{"x":2149.0,"y":2.0625},{"x":2210.5,"y":1.9861570869696057},{"x":2272.0,"y":1.9152640742890308},{"x":2333.5,"y":1.8492574950966656},{"x":2395.0,"y":1.7876489707475622},{"x":2456.5,"y":1.7300131061598951},{"x":2518.0,"y":1.675977653631285},{"x":2579.5,"y":1.6252154641713863},{"x":2641.0,"y":1.5774378585086042},{"x":2702.5,"y":1.5323891339679592},{"x":2764.0,"y":1.4898419864559818},{"x":2825.5,"y":1.4495936745003295},{"x":2887.0,"y":1.4114627887082978},{"x":2948.5,"y":1.3752865180245883},{"x":3010.0,"y":1.3409183258837871},{"x":3071.5,"y":1.3082259663032705},{"x":3133.0,"y":1.2770897832817336},{"x":3194.5,"y":1.2474012474012475},{"x":3256.0,"y":1.2190616919098634},{"x":3317.5,"y":1.1919812172656672},{"x":3379.0,"y":1.1660777385159011},{"x":3440.5,"y":1.1412761542452015},{"x":3502.0,"y":1.117507619370132},{"x":3563.5,"y":1.0947089069497429},{"x":3625.0,"y":1.0728218465539663},{"x":3686.5,"y":1.051792828685259},{"x":3748.0,"y":1.031572366364489},{"x":3809.5,"y":1.0121147063333844},{"x":3871.0,"y":0.9933774834437087},{"x":3932.5,"y":0.9753214127382887},{"x":3994.0,"y":0.9579100145137881},{"x":4055.5,"y":0.9411093683159847},{"x":4117.0,"y":0.9248878923766816},{"x":4178.5,"y":0.9092161454745833},{"x":4240.0,"y":0.8940666486047142},{"x":4301.5,"y":0.8794137241838773},{"x":4363.0,"y":0.865233350812795},{"x":4424.5,"y":0.8515030318668559},{"x":4486.0,"y":0.8382016764033527},{"x":4547.5,"y":0.8253094910591472},{"x":4609.0,"y":0.812807881773399},{"x":4670.5,"y":0.8006793643091107},{"x":4732.0,"y":0.7889074826679416},{"x":4793.5,"y":0.7774767345977147},{"x":4855.0,"y":0.7663725034835114},{"x":4916.5,"y":0.7555809959931311},{"x":4978.0,"y":0.7450891849175887},{"x":5039.5,"y":0.734884756708607},{"x":5101.0,"y":0.7249560632688928},{"x":5162.5,"y":0.7152920775983527},{"x":5224.0,"y":0.7058823529411764},{"x":5285.5,"y":0.6967169851155917},{"x":5347.0,"y":0.6877865777407254},{"x":5408.5,"y":0.6790822101039201},{"x":5470.0,"y":0.6705954074375127},{"x":5531.5,"y":0.6623181133968892},{"x":5593.0,"y":0.6542426645519429},{"x":5654.5,"y":0.6463617667221624},{"x":5716.0,"y":0.6386684730017419},{"x":5777.5,"y":0.6311561633355647},{"x":5839.0,"y":0.6238185255198487},{"x":5900.5,"y":0.6166495375128469},{"x":5962.0,"y":0.6096434509514133},{"x":6023.5,"y":0.60279477577861},{"x":6085.0,"y":0.5960982658959538},{"x":6146.5,"y":0.5895489057615007},{"x":6208.0,"y":0.583141897861813},{"x":6269.5,"y":0.5768726509920462},{"x":6331.0,"y":0.5707367692839848},{"x":6392.5,"y":0.5647300419269273},{"x":6454.0,"y":0.5588484335309061},{"x":6515.5,"y":0.5530880750858962},{"x":6577.0,"y":0.5474452554744526},{"x":6638.5,"y":0.5419164134986453}]}],"marks":[{"type":"line","from":{"data":"2077d49b-3fa7-4f54-9024-2f77290bb108"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"interpolate":{"value":"step-before"},"fill":{"value":"steelblue"},"fillOpacity":{"value":0.4},"stroke":{"value":"steelblue"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}},{"type":"line","from":{"data":"45302dca-2d46-40a7-b045-50b1e35a4467"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"stroke":{"value":"#FF29D2"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}}]},"value":"#gorilla_repl.vega.VegaView{:content {:width 400, :height 247.2188, :padding {:bottom 20, :top 10, :right 10, :left 50}, :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain [0 6700]} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"2077d49b-3fa7-4f54-9024-2f77290bb108\", :field \"data.y\"}}], :axes [{:scale \"x\", :type \"x\"} {:scale \"y\", :type \"y\"}], :data ({:name \"2077d49b-3fa7-4f54-9024-2f77290bb108\", :values ({:x 0.0, :y 0} {:x 117.54385964912282, :y 9.0} {:x 235.08771929824564, :y 11.0} {:x 352.63157894736844, :y 15.0} {:x 470.1754385964913, :y 12.0} {:x 587.7192982456141, :y 12.0} {:x 705.263157894737, :y 32.0} {:x 822.8070175438598, :y 26.0} {:x 940.3508771929827, :y 13.0} {:x 1057.8947368421054, :y 11.0} {:x 1175.4385964912283, :y 7.0} {:x 1292.9824561403511, :y 9.0} {:x 1410.526315789474, :y 6.0} {:x 1528.0701754385968, :y 5.0} {:x 1645.6140350877197, :y 4.0} {:x 1763.1578947368425, :y 5.0} {:x 1880.7017543859654, :y 2.0} {:x 1998.2456140350882, :y 3.0} {:x 2115.789473684211, :y 1.0} {:x 2233.3333333333335, :y 1.0} {:x 2350.877192982456, :y 2.0} {:x 2468.4210526315787, :y 1.0} {:x 2585.9649122807014, :y 2.0} {:x 2703.508771929824, :y 2.0} {:x 2821.0526315789466, :y 1.0} {:x 2938.596491228069, :y 0.0} {:x 3056.140350877192, :y 1.0} {:x 3173.6842105263145, :y 1.0} {:x 3291.228070175437, :y 1.0} {:x 3408.7719298245597, :y 0.0} {:x 3526.3157894736823, :y 0.0} {:x 3643.859649122805, :y 1.0} {:x 3761.4035087719276, :y 0.0} {:x 3878.94736842105, :y 0.0} {:x 3996.491228070173, :y 0.0} {:x 4114.035087719296, :y 0.0} {:x 4231.578947368419, :y 0.0} {:x 4349.122807017542, :y 2.0} {:x 4466.666666666665, :y 0.0} {:x 4584.210526315788, :y 0.0} {:x 4701.754385964911, :y 0.0} {:x 4819.298245614034, :y 0.0} {:x 4936.8421052631575, :y 1.0} {:x 5054.3859649122805, :y 0.0} {:x 5171.929824561404, :y 0.0} {:x 5289.473684210527, :y 0.0} {:x 5407.01754385965, :y 0.0} {:x 5524.561403508773, :y 0.0} {:x 5642.105263157896, :y 0.0} {:x 5759.649122807019, :y 1.0} {:x 5877.192982456142, :y 1.0} {:x 5994.736842105265, :y 0.0} {:x 6112.280701754388, :y 0.0} {:x 6229.824561403511, :y 1.0} {:x 6347.368421052634, :y 1.0} {:x 6464.9122807017575, :y 1.0} {:x 6582.456140350881, :y 0.0} {:x 6700.000000000004, :y 0.0} {:x 6817.543859649127, :y 0})} {:name \"45302dca-2d46-40a7-b045-50b1e35a4467\", :values ({:x 550, :y 3300N} {:x 611.5, :y 52.8} {:x 673.0, :y 26.612903225806452} {:x 734.5, :y 17.78975741239892} {:x 796.0, :y 13.360323886639677} {:x 857.5, :y 10.696920583468394} {:x 919.0, :y 8.91891891891892} {:x 980.5, :y 7.647740440324449} {:x 1042.0, :y 6.69371196754564} {:x 1103.5, :y 5.951307484220018} {:x 1165.0, :y 5.357142857142857} {:x 1226.5, :y 4.8708487084870855} {:x 1288.0, :y 4.465493910690122} {:x 1349.5, :y 4.122423485321674} {:x 1411.0, :y 3.8283062645011596} {:x 1472.5, :y 3.573362208987547} {:x 1534.0, :y 3.3502538071065993} {:x 1595.5, :y 3.153368370759675} {:x 1657.0, :y 2.9783393501805056} {:x 1718.5, :y 2.821718683197948} {:x 1780.0, :y 2.680747359870024} {:x 1841.5, :y 2.5531914893617023} {:x 1903.0, :y 2.4372230428360413} {:x 1964.5, :y 2.3313316849169903} {:x 2026.0, :y 2.2342586323628977} {:x 2087.5, :y 2.1449463763405916} {:x 2149.0, :y 2.0625} {:x 2210.5, :y 1.9861570869696057} {:x 2272.0, :y 1.9152640742890308} {:x 2333.5, :y 1.8492574950966656} {:x 2395.0, :y 1.7876489707475622} {:x 2456.5, :y 1.7300131061598951} {:x 2518.0, :y 1.675977653631285} {:x 2579.5, :y 1.6252154641713863} {:x 2641.0, :y 1.5774378585086042} {:x 2702.5, :y 1.5323891339679592} {:x 2764.0, :y 1.4898419864559818} {:x 2825.5, :y 1.4495936745003295} {:x 2887.0, :y 1.4114627887082978} {:x 2948.5, :y 1.3752865180245883} {:x 3010.0, :y 1.3409183258837871} {:x 3071.5, :y 1.3082259663032705} {:x 3133.0, :y 1.2770897832817336} {:x 3194.5, :y 1.2474012474012475} {:x 3256.0, :y 1.2190616919098634} {:x 3317.5, :y 1.1919812172656672} {:x 3379.0, :y 1.1660777385159011} {:x 3440.5, :y 1.1412761542452015} {:x 3502.0, :y 1.117507619370132} {:x 3563.5, :y 1.0947089069497429} {:x 3625.0, :y 1.0728218465539663} {:x 3686.5, :y 1.051792828685259} {:x 3748.0, :y 1.031572366364489} {:x 3809.5, :y 1.0121147063333844} {:x 3871.0, :y 0.9933774834437087} {:x 3932.5, :y 0.9753214127382887} {:x 3994.0, :y 0.9579100145137881} {:x 4055.5, :y 0.9411093683159847} {:x 4117.0, :y 0.9248878923766816} {:x 4178.5, :y 0.9092161454745833} {:x 4240.0, :y 0.8940666486047142} {:x 4301.5, :y 0.8794137241838773} {:x 4363.0, :y 0.865233350812795} {:x 4424.5, :y 0.8515030318668559} {:x 4486.0, :y 0.8382016764033527} {:x 4547.5, :y 0.8253094910591472} {:x 4609.0, :y 0.812807881773399} {:x 4670.5, :y 0.8006793643091107} {:x 4732.0, :y 0.7889074826679416} {:x 4793.5, :y 0.7774767345977147} {:x 4855.0, :y 0.7663725034835114} {:x 4916.5, :y 0.7555809959931311} {:x 4978.0, :y 0.7450891849175887} {:x 5039.5, :y 0.734884756708607} {:x 5101.0, :y 0.7249560632688928} {:x 5162.5, :y 0.7152920775983527} {:x 5224.0, :y 0.7058823529411764} {:x 5285.5, :y 0.6967169851155917} {:x 5347.0, :y 0.6877865777407254} {:x 5408.5, :y 0.6790822101039201} {:x 5470.0, :y 0.6705954074375127} {:x 5531.5, :y 0.6623181133968892} {:x 5593.0, :y 0.6542426645519429} {:x 5654.5, :y 0.6463617667221624} {:x 5716.0, :y 0.6386684730017419} {:x 5777.5, :y 0.6311561633355647} {:x 5839.0, :y 0.6238185255198487} {:x 5900.5, :y 0.6166495375128469} {:x 5962.0, :y 0.6096434509514133} {:x 6023.5, :y 0.60279477577861} {:x 6085.0, :y 0.5960982658959538} {:x 6146.5, :y 0.5895489057615007} {:x 6208.0, :y 0.583141897861813} {:x 6269.5, :y 0.5768726509920462} {:x 6331.0, :y 0.5707367692839848} {:x 6392.5, :y 0.5647300419269273} {:x 6454.0, :y 0.5588484335309061} {:x 6515.5, :y 0.5530880750858962} {:x 6577.0, :y 0.5474452554744526} {:x 6638.5, :y 0.5419164134986453})}), :marks ({:type \"line\", :from {:data \"2077d49b-3fa7-4f54-9024-2f77290bb108\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :interpolate {:value \"step-before\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 0.4}, :stroke {:value \"steelblue\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}} {:type \"line\", :from {:data \"45302dca-2d46-40a7-b045-50b1e35a4467\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :stroke {:value \"#FF29D2\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}})}}"}
;; <=

;; @@
;; Output dat file for irkalla
#_(->> monies
     (interpose \newline)
     (reduce str)
     (spit "/home/nmccarty/monies.txt"))
;; @@

;; @@
;; Takes the average of the set
(defn average [coll]
  (double (/ (reduce + coll)
             (count coll))))

;; Takes the mode of the set
(defn mode [coll]
  (->> coll
       (frequencies)
       (reduce (fn [[x1 x2] [y1 y2]]
                 (if (> x2 y2)
                   [x1 x2]
                   [y1 y2])))
       (first)))

;; Takes weighted average in a stupid way
(defn stupid-weighted-average [coll]
  (->> coll
       (sort >)
       (reduce #(/ (+ %1 %2) 2))
       (double)))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;economy-health/stupid-weighted-average</span>","value":"#'economy-health/stupid-weighted-average"}
;; <=

;; @@
(println "Average:")
(println (average monies))
(println)
(println "Weighed Average:")
(println (stupid-weighted-average monies))
(println)
(println "Mode:")
(println (mode monies))
(println)
(println "Average/Weighted average")
(println (/ (average monies)
            (stupid-weighted-average monies)))
(println)
(println "Average/Mode")
(println (/ (average monies)
            (mode monies)))
;; @@
;; ->
;;; Average:
;;; 33917.13333333333
;;; 
;;; Weighed Average:
;;; 25.649580417338
;;; 
;;; Mode:
;;; 680
;;; 
;;; Average/Weighted average
;;; 1322.3270237359059
;;; 
;;; Average/Mode
;;; 49.87813725490196
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; The percentage of data points below 49132, for irkalla.
;; **

;; @@
(println
 (format "%.3f%%"
         (* 100
            (double (/ (count (filter #(< % 49132)
                                      monies))
                       (count monies))))))
;; @@
;; ->
;;; 89.259%
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; **
;;; The percentage of money accounted for by people having less than 49132 dollars.
;; **

;; @@
(println 
 (format "%.3f%%"
         (* 100
            (double (/ (reduce +
                               (filter #(< % 49132) monies))
                       (reduce + monies))))))
;; @@
;; ->
;;; 9.814%
;;; 
;; <-
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=
