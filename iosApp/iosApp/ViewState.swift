//
//  ViewState.swift
//  iosApp
//
//  Created by Sergey Prybysh on 5/29/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

enum ViewState<Success, Error> {
    case error(Error)
    case loading
    case success(Success)
}
