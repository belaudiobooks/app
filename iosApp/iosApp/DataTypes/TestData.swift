//
//  TestData.swift
//  iosApp
//
//  Created by Sergey Prybysh on 9/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

let testBooks = [
  BookCover(
    uuid: "1",
    title: "Book One",
    coverImageURL: "https://storage.googleapis.com/books_media/covers/liubits-noch-prava-patsukou.jpg",
    date: Kotlinx_datetimeLocalDate(year: 1996, month: Kotlinx_datetimeMonth.august, dayOfMonth: 12).date,
    description: "Description One",
    descriptionSource: "Source One",
    authors: [],
    tags: []),
  BookCover(
    uuid: "2",
    title: "Book Two",
    coverImageURL: "https://storage.googleapis.com/books_media/covers/vershy-dlia-dziatsei.jpg",
    date: Kotlinx_datetimeLocalDate(year: 1996, month: Kotlinx_datetimeMonth.august, dayOfMonth: 12).date,
    description: "Description One",
    descriptionSource: "Source One",
    authors: [],
    tags: []),
  BookCover(
    uuid: "3",
    title: "Book Three",
    coverImageURL: "https://storage.googleapis.com/books_media/covers/orden-belai-myshy-eb2d7db0-37ac-4ed4-b1fd-6e47bf991f87.jpeg",
    date: Kotlinx_datetimeLocalDate(year: 1996, month: Kotlinx_datetimeMonth.august, dayOfMonth: 12).date,
    description: "Description One",
    descriptionSource: "Source One",
    authors: [],
    tags: []),
  BookCover(
    uuid: "4",
    title: "Book Four",
    coverImageURL: "https://storage.googleapis.com/books_media/covers/kazka-pra-toe-iak-sviatoga-mikolu-zabaraniali.jpeg",
    date: Kotlinx_datetimeLocalDate(year: 1996, month: Kotlinx_datetimeMonth.august, dayOfMonth: 12).date,
    description: "Description One",
    descriptionSource: "Source One",
    authors: [],
    tags: [])
]


