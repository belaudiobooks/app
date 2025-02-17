//
//  BookCollectionView.swift
//  iosApp
//
//  Created by Sergey Prybysh on 2/8/25.
//  Copyright © 2025 orgName. All rights reserved.
//

import SwiftUI

struct BookCollectionView: ComponentView {
  @ObservedObject var store: Store<BookCollectionViewModel.State, BookCollectionViewModel.Action>
  
  var body: some View {
    BookGridView(
      books: store.state.books,
      action: { bookID in store.handle(.selctedBookCover(id: bookID)) } )
  }
}
