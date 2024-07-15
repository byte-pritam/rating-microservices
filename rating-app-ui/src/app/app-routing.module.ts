import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserComponent } from './componenets/user/user.component';
import { HotelComponent } from './componenets/hotel/hotel.component';
import { RatingComponent } from './componenets/rating/rating.component';
import { AppComponent } from './app.component';
import { HomeComponent } from './componenets/home/home.component';

const routes: Routes = [
  { path: 'users', component: UserComponent },
  { path: 'hotel', component: HotelComponent},
  { path: 'rating', component: RatingComponent},
  { path: 'home', component: HomeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
