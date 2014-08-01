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

;; @@
(def monies (->> player-file
                 (string/split-lines)
                 (map #(string/split % #";"))
                 (drop 26)
                 (map last)
                 (map #(Long/parseLong %))
                 (filter #(> % 2000))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;economy-health/monies</span>","value":"#'economy-health/monies"}
;; <=

;; @@
(count monies)
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-unkown'>92</span>","value":"92"}
;; <=

;; @@
(take 10 monies)
;; @@
;; =>
;;; {"type":"list-like","open":"<span class='clj-lazy-seq'>(</span>","close":"<span class='clj-lazy-seq'>)</span>","separator":" ","items":[{"type":"html","content":"<span class='clj-long'>152065</span>","value":"152065"},{"type":"html","content":"<span class='clj-long'>9954364</span>","value":"9954364"},{"type":"html","content":"<span class='clj-long'>3275</span>","value":"3275"},{"type":"html","content":"<span class='clj-long'>7890</span>","value":"7890"},{"type":"html","content":"<span class='clj-long'>175613</span>","value":"175613"},{"type":"html","content":"<span class='clj-long'>223465</span>","value":"223465"},{"type":"html","content":"<span class='clj-long'>540817</span>","value":"540817"},{"type":"html","content":"<span class='clj-long'>609296</span>","value":"609296"},{"type":"html","content":"<span class='clj-long'>444347</span>","value":"444347"},{"type":"html","content":"<span class='clj-long'>358203</span>","value":"358203"}],"value":"(152065 9954364 3275 7890 175613 223465 540817 609296 444347 358203)"}
;; <=

;; @@
(plot/histogram monies 
                :bins 50
                :plot-range [:all :all])
;; @@
;; =>
;;; {"type":"vega","content":{"axes":[{"scale":"x","type":"x"},{"scale":"y","type":"y"}],"scales":[{"name":"x","type":"linear","range":"width","zero":false,"domain":{"data":"ff4da844-dcc9-4262-9e23-1d25f8c1b300","field":"data.x"}},{"name":"y","type":"linear","range":"height","nice":true,"zero":false,"domain":{"data":"ff4da844-dcc9-4262-9e23-1d25f8c1b300","field":"data.y"}}],"marks":[{"type":"line","from":{"data":"ff4da844-dcc9-4262-9e23-1d25f8c1b300"},"properties":{"enter":{"x":{"scale":"x","field":"data.x"},"y":{"scale":"y","field":"data.y"},"interpolate":{"value":"step-before"},"fill":{"value":"steelblue"},"fillOpacity":{"value":0.4},"stroke":{"value":"steelblue"},"strokeWidth":{"value":2},"strokeOpacity":{"value":1}}}}],"data":[{"name":"ff4da844-dcc9-4262-9e23-1d25f8c1b300","values":[{"x":2030.0,"y":0},{"x":201076.68000000005,"y":76.0},{"x":400123.3600000001,"y":5.0},{"x":599170.0400000002,"y":3.0},{"x":798216.7200000002,"y":2.0},{"x":997263.4000000003,"y":2.0},{"x":1196310.0800000003,"y":0.0},{"x":1395356.7600000002,"y":3.0},{"x":1594403.4400000004,"y":0.0},{"x":1793450.1200000006,"y":0.0},{"x":1992496.8000000007,"y":0.0},{"x":2191543.480000001,"y":0.0},{"x":2390590.160000001,"y":0.0},{"x":2589636.8400000012,"y":0.0},{"x":2788683.5200000014,"y":0.0},{"x":2987730.2000000016,"y":0.0},{"x":3186776.8800000018,"y":0.0},{"x":3385823.560000002,"y":0.0},{"x":3584870.240000002,"y":0.0},{"x":3783916.9200000023,"y":0.0},{"x":3982963.6000000024,"y":0.0},{"x":4182010.2800000026,"y":0.0},{"x":4381056.960000003,"y":0.0},{"x":4580103.640000002,"y":0.0},{"x":4779150.320000002,"y":0.0},{"x":4978197.000000002,"y":0.0},{"x":5177243.680000002,"y":0.0},{"x":5376290.360000001,"y":0.0},{"x":5575337.040000001,"y":0.0},{"x":5774383.720000001,"y":0.0},{"x":5973430.4,"y":0.0},{"x":6172477.08,"y":0.0},{"x":6371523.76,"y":0.0},{"x":6570570.4399999995,"y":0.0},{"x":6769617.119999999,"y":0.0},{"x":6968663.799999999,"y":0.0},{"x":7167710.479999999,"y":0.0},{"x":7366757.159999998,"y":0.0},{"x":7565803.839999998,"y":0.0},{"x":7764850.519999998,"y":0.0},{"x":7963897.199999997,"y":0.0},{"x":8162943.879999997,"y":0.0},{"x":8361990.559999997,"y":0.0},{"x":8561037.239999996,"y":0.0},{"x":8760083.919999996,"y":0.0},{"x":8959130.599999996,"y":0.0},{"x":9158177.279999996,"y":0.0},{"x":9357223.959999995,"y":0.0},{"x":9556270.639999995,"y":0.0},{"x":9755317.319999995,"y":0.0},{"x":9954363.999999994,"y":0.0},{"x":1.0153410679999994E7,"y":1.0},{"x":1.0352457359999994E7,"y":0}]}],"width":400,"height":247.2187957763672,"padding":{"bottom":20,"top":10,"right":10,"left":50}},"value":"#gorilla_repl.vega.VegaView{:content {:axes [{:scale \"x\", :type \"x\"} {:scale \"y\", :type \"y\"}], :scales [{:name \"x\", :type \"linear\", :range \"width\", :zero false, :domain {:data \"ff4da844-dcc9-4262-9e23-1d25f8c1b300\", :field \"data.x\"}} {:name \"y\", :type \"linear\", :range \"height\", :nice true, :zero false, :domain {:data \"ff4da844-dcc9-4262-9e23-1d25f8c1b300\", :field \"data.y\"}}], :marks [{:type \"line\", :from {:data \"ff4da844-dcc9-4262-9e23-1d25f8c1b300\"}, :properties {:enter {:x {:scale \"x\", :field \"data.x\"}, :y {:scale \"y\", :field \"data.y\"}, :interpolate {:value \"step-before\"}, :fill {:value \"steelblue\"}, :fillOpacity {:value 0.4}, :stroke {:value \"steelblue\"}, :strokeWidth {:value 2}, :strokeOpacity {:value 1}}}}], :data [{:name \"ff4da844-dcc9-4262-9e23-1d25f8c1b300\", :values ({:x 2030.0, :y 0} {:x 201076.68000000005, :y 76.0} {:x 400123.3600000001, :y 5.0} {:x 599170.0400000002, :y 3.0} {:x 798216.7200000002, :y 2.0} {:x 997263.4000000003, :y 2.0} {:x 1196310.0800000003, :y 0.0} {:x 1395356.7600000002, :y 3.0} {:x 1594403.4400000004, :y 0.0} {:x 1793450.1200000006, :y 0.0} {:x 1992496.8000000007, :y 0.0} {:x 2191543.480000001, :y 0.0} {:x 2390590.160000001, :y 0.0} {:x 2589636.8400000012, :y 0.0} {:x 2788683.5200000014, :y 0.0} {:x 2987730.2000000016, :y 0.0} {:x 3186776.8800000018, :y 0.0} {:x 3385823.560000002, :y 0.0} {:x 3584870.240000002, :y 0.0} {:x 3783916.9200000023, :y 0.0} {:x 3982963.6000000024, :y 0.0} {:x 4182010.2800000026, :y 0.0} {:x 4381056.960000003, :y 0.0} {:x 4580103.640000002, :y 0.0} {:x 4779150.320000002, :y 0.0} {:x 4978197.000000002, :y 0.0} {:x 5177243.680000002, :y 0.0} {:x 5376290.360000001, :y 0.0} {:x 5575337.040000001, :y 0.0} {:x 5774383.720000001, :y 0.0} {:x 5973430.4, :y 0.0} {:x 6172477.08, :y 0.0} {:x 6371523.76, :y 0.0} {:x 6570570.4399999995, :y 0.0} {:x 6769617.119999999, :y 0.0} {:x 6968663.799999999, :y 0.0} {:x 7167710.479999999, :y 0.0} {:x 7366757.159999998, :y 0.0} {:x 7565803.839999998, :y 0.0} {:x 7764850.519999998, :y 0.0} {:x 7963897.199999997, :y 0.0} {:x 8162943.879999997, :y 0.0} {:x 8361990.559999997, :y 0.0} {:x 8561037.239999996, :y 0.0} {:x 8760083.919999996, :y 0.0} {:x 8959130.599999996, :y 0.0} {:x 9158177.279999996, :y 0.0} {:x 9357223.959999995, :y 0.0} {:x 9556270.639999995, :y 0.0} {:x 9755317.319999995, :y 0.0} {:x 9954363.999999994, :y 0.0} {:x 1.0153410679999994E7, :y 1.0} {:x 1.0352457359999994E7, :y 0})}], :width 400, :height 247.2188, :padding {:bottom 20, :top 10, :right 10, :left 50}}}"}
;; <=

;; @@

;; @@
