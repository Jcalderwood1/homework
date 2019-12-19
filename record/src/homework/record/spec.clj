(ns homework.record.spec
  (:require [homework.record.date    :as date]
            [clojure.spec.alpha      :as s]
            [clojure.spec.gen.alpha  :as gen]
            [talltale.core           :as tt]
            [clojure.string          :as str]
            [expound.alpha           :as expound]))

;; last-name spec
(s/def ::last-name      (s/with-gen
                          (s/and string?
                                 #(re-matches #"[a-zA-Z].*" %))
                          (fn [] (gen/fmap
                                   (fn [x] (tt/last-name))
                                   (gen/string-alphanumeric)))))
(expound/defmsg ::last-name "Should be a string of at least one alpha character")

;; first-name spec
(s/def ::first-name     (s/with-gen
                          (s/and string?
                                 #(re-matches #"[a-zA-Z].*" %))
                          (fn [] (gen/fmap
                                   (fn [x] (tt/first-name))
                                   (gen/string-alphanumeric)))))
(expound/defmsg ::first-name "Should be a string of at least one alpha character")

;; gender spec
(s/def ::gender         #{"male" "female"})

;; favorite-color spec
(s/def ::favorite-color (s/with-gen
                          (s/and string?
                                 #(re-matches #"[a-zA-Z].*" %))
                          (fn [] (gen/fmap
                                   (fn [x] (first (str/split (tt/color) #" ")))
                                   (gen/string-alphanumeric)))))
(expound/defmsg ::favorite-color "Should be a string of at least one alpha character")

;; date-of-birth spec
(defn date-regex [date] (re-matches #"^(0?[1-9]|1[0-2])[\/](0?[1-9]|[12]\d|3[01])[\/](19|20)\d{2}$" date))
(s/def ::date-of-birth (s/with-gen
                         (s/and string?
                                date-regex
                                date/dob->inst)
                         (fn [] (gen/fmap
                                  (fn [x] (date/tuple->dob x))
                                  (s/gen (s/tuple (s/int-in 1910 2019)
                                                  (s/int-in 1 13)
                                                  (s/int-in 1 32)))))))
(expound/defmsg ::date-of-birth "Should be a valid date of format M/D/YYYY between 1900-2099")

;; record spec
(s/def ::record (s/keys :req-un [::last-name ::first-name ::gender ::favorite-color ::date-of-birth]))

;; db spec
(s/def ::db (s/coll-of ::record :into []))
