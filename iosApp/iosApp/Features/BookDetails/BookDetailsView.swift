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
      if let book = store.state.book {
        ScrollView(.vertical) {
          VStack(spacing: 16) {
            Text(book.title)
              .font(.title)
            
            HStack {
              Spacer()
              Text(book.authors.first?.name ?? "")
                .font(.title3)
                .foregroundStyle(.gray)
            }.padding(.trailing, 60)
            
            Text(book.description)
              .font(.system(size: 14))
              .padding(.horizontal, 20)
              .padding(.top, 20)
            
            HStack {
              Text("Narrations")
                .font(.title3)
              Spacer()
            }
            .padding(.horizontal, 8)
            
            ForEach(book.narrations) { narration in
              NarrationView(narration: narration)
                .padding(.horizontal, 8)
            }
            
            HStack {
              Text("Streaming Servces")
                .font(.title3)
              Spacer()
            }
            .padding(.horizontal, 8)
            
          }
        }
      } else {
        Text("Loading...")
      }
    }
}

#Preview {
  BookDetailsView(store: .init(
    initialState: BookDetailsViewModel.State(book: testBookDetails),
    handler: nil))
}
