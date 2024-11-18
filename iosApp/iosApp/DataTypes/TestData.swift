//
//  TestData.swift
//  iosApp
//
//  Created by Sergey Prybysh on 9/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

let testBooks = [
  BookCover(
    uuid: "1",
    title: "Book One",
    coverImageURL: ["https://storage.googleapis.com/books_media/covers/liubits-noch-prava-patsukou.jpg"],
    description: "Description One",
    authors: [],
    tags: []),
  BookCover(
    uuid: "2",
    title: "Book Two",
    coverImageURL: ["https://storage.googleapis.com/books_media/covers/vershy-dlia-dziatsei.jpg"],
    description: "Description One",
    authors: [],
    tags: []),
  BookCover(
    uuid: "3",
    title: "Book Three",
    coverImageURL: ["https://storage.googleapis.com/books_media/covers/orden-belai-myshy-eb2d7db0-37ac-4ed4-b1fd-6e47bf991f87.jpeg"],
    description: "Description One",
    authors: [],
    tags: []),
  BookCover(
    uuid: "4",
    title: "Book Four",
    coverImageURL: ["https://storage.googleapis.com/books_media/covers/kazka-pra-toe-iak-sviatoga-mikolu-zabaraniali.jpeg"],
    description: "Description One",
    authors: [],
    tags: [])
]

let testBookDetails = BookDetails(
  uuid: UUID().uuidString,
  title: "Доўгая дарога дадому",
  narrations: [
    NarrationDetails(
      uuid: UUID().uuidString,
      coverImageURL: "https://storage.googleapis.com/books_media/covers/orden-belai-myshy-eb2d7db0-37ac-4ed4-b1fd-6e47bf991f87.jpeg",
      narrator: "Іван Свістулькін",
      publisher: "audiobooks.by",
      language: "беларуская",
      duration: "16 гадзін 52 хвіліны",
      cost: true,
      streamingServices: [])
  ],
  description: """
   Гэта апошняя і найбольш спавядальная кніга Васіля Быкава. Яна была напісана на схіле гадоў і на чужыне, калі ішоў апошні акт драмы пісьменніка: у 1998 г. ён вымушаны быў пакінуць Беларусь. Фінляндыя, Германія, Чэхія — гэтыя краіны далі прыстанішча таму, каго на радзіме называлі сумленнем нацыі.
   """,
  authors: [Author(uuid: UUID().uuidString, name: "Васіль Быкаў")],
  tags: [])

