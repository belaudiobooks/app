//
//  BookDetailsView.swift
//  iosApp
//
//  Created by Sergey Prybysh on 9/1/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct BookDetailsView: ComponentView {
  @ObservedObject var store: Store<BookDetailsViewModel.State, BookDetailsViewModel.Action>
  
    var body: some View {
      Text(store.state.book.title)
    }
}
